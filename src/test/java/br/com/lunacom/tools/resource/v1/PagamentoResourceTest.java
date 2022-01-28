package br.com.lunacom.tools.resource.v1;

import br.com.lunacom.tools.converter.PagamentoEntityToResponseConverter;
import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.request.PagamentoRequest;
import br.com.lunacom.tools.repository.PagamentoRepository;
import br.com.lunacom.tools.service.PagamentoService;
import br.com.lunacom.tools.util.Comuns;
import br.com.lunacom.tools.util.JsonLoader;
import builder.PagamentoEntityBuilder;
import builder.PagamentoRequestBuilder;
import builder.PagamentoResponseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PagamentoResourceTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    HttpServletRequest request;

    static final String URL = "/v1/pagamento/";
    private ObjectMapper objectMapper;
    private final String JSON_PAGAMENTO_REQUEST_SEM_ATRIBUTOS = "PagamentoRequestSemAtributos.json";
    private final String JSON_PAGAMENTO_REQUEST_SEM_OBJETOS = "PagamentoRequestSemObjetos.json";

    @MockBean
    PagamentoEntityToResponseConverter pagamentoEntityToResponseConverter;
    @MockBean
    PagamentoService service;
    @MockBean
    PagamentoRepository repository;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    @DisplayName("Novo pagamento criado com sucesso")
    public void criarComSucesso() throws Exception {
        PagamentoRequest pagamentoRequest = PagamentoRequestBuilder.umPagamento().agora();
        PagamentoEntity entity = PagamentoEntityBuilder.umPagamento().agora();
        String json = objectMapper.writeValueAsString(pagamentoRequest);
        given(service.salvar(any(PagamentoEntity.class)))
                .willReturn(entity);
        given(pagamentoEntityToResponseConverter.encode(any(PagamentoEntity.class)))
                .willReturn(PagamentoResponseBuilder.umPagamento().agora());

        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);

        mvc
            .perform(request)
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.transacao.cartao").value("4465484984612312"))
            .andExpect(jsonPath("$.transacao.id").value("1064654654565451L"))
            .andExpect(jsonPath("$.transacao.descricao.valor").value("625.00"))
            .andExpect(jsonPath("$.transacao.descricao.dataHora").value("01/08/2022 10:30:00"))
            .andExpect(jsonPath("$.transacao.descricao.estabelecimento").value("Colégio Pinho"))
            .andExpect(jsonPath("$.transacao.formaPagamento.tipo").value("AVISTA"))
            .andExpect(jsonPath("$.transacao.formaPagamento.parcelas").value(5));
    }

    @Test
    @DisplayName("Deve lançar erro de validação")
    public void erroValidacao() throws Exception {
        String json = JsonLoader.getContentFromFile(JSON_PAGAMENTO_REQUEST_SEM_ATRIBUTOS);
        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);
        mvc
                .perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.mensagem").value("Verifique os seguintes itens antes de avançar"))
                .andExpect(jsonPath("$.detalhe[0]").value("Informe a data e hora"))
                .andExpect(jsonPath("$.detalhe[1]").value("Informe a quantidade de parcelas"))
                .andExpect(jsonPath("$.detalhe[2]").value("Informe o ID"))
                .andExpect(jsonPath("$.detalhe[3]").value("Informe o estabelecimento"))
                .andExpect(jsonPath("$.detalhe[4]").value("Informe o número do cartão"))
                .andExpect(jsonPath("$.detalhe[5]").value("Informe o tipo"))
                .andExpect(jsonPath("$.detalhe[6]").value("Informe o valor"));

    }

    @Test
    @DisplayName("Deve lançar erro de validação quando recebe uma request sem objetos Descricao e FormaPagamento")
    public void erroValidacaoRequestSemObjetos() throws Exception {
        String json = JsonLoader.getContentFromFile(JSON_PAGAMENTO_REQUEST_SEM_OBJETOS);
        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);
        mvc
                .perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.mensagem").value("Verifique os seguintes itens antes de avançar"))
                .andExpect(jsonPath("$.detalhe[0]").value("Objeto Descricao deve ser informado"))
                .andExpect(jsonPath("$.detalhe[1]").value("Objeto FormaPagamento deve ser informado"));
    }

    @Test
    @DisplayName("Deve impedir um pagamento para um ID já existente")
    public void impedirPagamentoDuplicadoPorId() throws Exception {
        PagamentoRequest pagamentoRequest = PagamentoRequestBuilder.umPagamento().agora();
        PagamentoEntity entity = PagamentoEntityBuilder.umPagamento().agora();
        String json = objectMapper.writeValueAsString(pagamentoRequest);
        given(repository.findById(any(Long.class)))
                .willReturn(Optional.of(entity));

        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);

        mvc
                .perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.mensagem").value("Verifique os seguintes itens antes de avançar"))
                .andExpect(jsonPath("$.detalhe[0]").value("O Pagamento informado já existe no sistema"));
    }

    @Test
    @DisplayName("Deve impedir que seja informada Data e Hora diferente do padrão dd/MM/yyyy H:mm:ss")
    public void validarPadraoDeDataHora() throws Exception {
        PagamentoRequest pagamentoRequest = PagamentoRequestBuilder
                .umPagamento()
                .dataMalFormatada()
                .agora();
        String json = objectMapper.writeValueAsString(pagamentoRequest);

        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);

        mvc
                .perform(request)
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.mensagem").value("Verifique os seguintes itens antes de avançar"))
                .andExpect(jsonPath("$.detalhe[0]").value("O campo data e hora deve estar no formato dd/mm/YYYY HH:mm:ss"));
    }

    @Test
    @DisplayName("Deve impedir que seja informada o campo Tipo (FormaPagamento.tipo) com valores diferentes dos possíveis")
    public void validarValoresPossiveisFormaPagamentoTipo() throws Exception {

    }

    @Test
    @DisplayName("Deve realizar um estorno com sucesso")
    public void estornarComSucesso() throws Exception {

    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o pagamento não for localizado")
    public void lancarExcecaoPorPagamentoNaoLocalizado() throws Exception {

    }

    @Test
    @DisplayName("Deve pesquisar por ID com sucesso")
    public void pesquisarPorId() throws Exception {

    }

    @Test
    @DisplayName("Deve retornar No Content Quanto pesquisa por ID não encontrar registro")
    public void deveRetornarNoContentQuandoIdNaoForEncontrado() throws Exception {

    }

    @Test
    @DisplayName("Deve retornar uma lista de Pagamentos ao consultar por Todos")
    public void deveRetornarUmaListaDePagamentos() throws Exception {

    }

}
