package builder;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import br.com.lunacom.tools.domain.response.DescricaoResponse;
import br.com.lunacom.tools.domain.response.FormaPagamentoResponse;
import br.com.lunacom.tools.domain.response.PagamentoResponse;
import br.com.lunacom.tools.domain.response.TransacaoResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagamentoResponseBuilder {
    private PagamentoResponse pagamentoResponse;

    public static PagamentoResponseBuilder umPagamento() {
        FormaPagamentoResponse formaPagamentoResponse = FormaPagamentoResponse.builder()
                .tipo(FormaPagamentoEnum.AVISTA)
                .parcelas(5)
                .build();
        DescricaoResponse descricaoResponse = DescricaoResponse.builder()
                .valor("625.00")
                .dataHora("01/08/2022 10:30:00")
                .estabelecimento("Colégio Pinho")
                .nsu("")
                .codigoAutorizacao("")
                .build();
        TransacaoResponse transacaoResponse = TransacaoResponse.builder()
                .cartao("4465484984612312")
                .id("1064654654565451L")
                .descricao(descricaoResponse)
                .formaPagamento(formaPagamentoResponse)
                .build();

        return new PagamentoResponseBuilder(new PagamentoResponse(transacaoResponse));
    }

    public PagamentoResponse agora() {
        return pagamentoResponse;
    }

    public PagamentoResponseBuilder status(StatusEnum novoStatus) {
        pagamentoResponse.getTransacao().getDescricao().setStatus(novoStatus);
        return this;
    }
}
