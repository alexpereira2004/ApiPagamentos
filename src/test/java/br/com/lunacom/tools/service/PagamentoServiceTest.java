package br.com.lunacom.tools.service;

import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.repository.DescricaoRepository;
import br.com.lunacom.tools.repository.PagamentoRepository;
import builder.PagamentoEntityBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PagamentoServiceTest {

    PagamentoService service;
    @MockBean
    PagamentoRepository repository;
    @MockBean
    DescricaoRepository descricaoRepository;

    @BeforeEach
    public void setUp() {
        this.service = new PagamentoService(repository, descricaoRepository);

//        this.mapper = new JsonResourceObjectMapper();
    }

    @Test
    @DisplayName("Deve criar um pagamento")
    public void salvarTest() {
        final PagamentoEntity pagamentoEntity = PagamentoEntityBuilder.umPagamento().agora();
        Mockito.when(repository.save(pagamentoEntity))
                .thenReturn(pagamentoEntity);

        PagamentoEntity response = service.salvar(pagamentoEntity);
        Assertions.assertThat(response.getId()).isEqualTo(1064654654565451L);
    }

    @Test
    @DisplayName("Deve estornar um pagamento")
    public void estornar() {

    }

    @Test
    @DisplayName("Deve lançar exceção ao estornar um pagamento")
    public void estornarComExcecao() {

    }

}
