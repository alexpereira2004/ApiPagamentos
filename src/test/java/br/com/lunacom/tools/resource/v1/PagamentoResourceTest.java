package br.com.lunacom.tools.resource.v1;

import br.com.lunacom.tools.converter.PagamentoEntityToResponseConverter;
import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.request.PagamentoRequest;
import br.com.lunacom.tools.service.PagamentoService;
import br.com.lunacom.tools.util.Comuns;
import builder.PagamentoEntityBuilder;
import builder.PagamentoRequestBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.servlet.http.HttpServletRequest;

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

    @MockBean
    PagamentoEntityToResponseConverter pagamentoEntityToResponseConverter;
    @MockBean
    PagamentoService service;

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
        BDDMockito
                .given(service.salvar(Mockito.any(PagamentoEntity.class)))
                .willReturn(entity);
        MockHttpServletRequestBuilder request = Comuns.getMockHttpServletRequestBuilder(URL, json);

        mvc
            .perform(request)
            .andExpect(MockMvcResultMatchers.status().isCreated());
    }


}
