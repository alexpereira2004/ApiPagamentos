package builder;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import br.com.lunacom.tools.domain.request.DescricaoRequest;
import br.com.lunacom.tools.domain.request.FormaPagamentoRequest;
import br.com.lunacom.tools.domain.request.PagamentoRequest;
import br.com.lunacom.tools.domain.request.TransacaoRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagamentoRequestBuilder {

    private PagamentoRequest pagamentoRequest;

    public static PagamentoRequestBuilder umPagamento() {
        FormaPagamentoRequest formaPagamentoRequest = FormaPagamentoRequest.builder()
                .tipo(FormaPagamentoEnum.AVISTA)
                .parcelas("5")
                .build();
        DescricaoRequest descricaoRequest = DescricaoRequest.builder()
                .valor("625.00")
                .dataHora("01/08/2022 10:30:00")
                .estabelecimento("Colégio Pinho")
                .nsu("")
                .codigoAutorizacao("")
                .build();
        TransacaoRequest transacaoRequest = TransacaoRequest.builder()
                .cartao("4465484984612312")
                .id("1064654654565451")
                .descricao(descricaoRequest)
                .formaPagamento(formaPagamentoRequest)
                .build();
        PagamentoRequest pagamentoRequest = PagamentoRequest.builder()
                .transacao(transacaoRequest)
                .build();
        return new PagamentoRequestBuilder(pagamentoRequest);
    }

    public PagamentoRequest agora() {
        return pagamentoRequest;
    }
}
