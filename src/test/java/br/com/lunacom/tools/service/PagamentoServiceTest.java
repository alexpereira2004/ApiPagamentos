package br.com.lunacom.tools.service;

import br.com.lunacom.tools.repository.DescricaoRepository;
import br.com.lunacom.tools.repository.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

public class PagamentoServiceTest {

    PagamentoService service;
    @MockBean
    PagamentoRepository pagamentoRepository;
    @MockBean
    DescricaoRepository descricaoRepository;

    @BeforeEach
    public void setUp() {
        this.service = new PagamentoService(pagamentoRepository, descricaoRepository);

//        this.mapper = new JsonResourceObjectMapper();
    }

    @Test
    @DisplayName("Deve criar um pagamento")
    public void salvarTest() {

    }
}
