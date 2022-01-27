package builder;

import br.com.lunacom.tools.domain.entity.DescricaoEntity;
import br.com.lunacom.tools.domain.entity.FormaPagamentoEntity;
import br.com.lunacom.tools.domain.entity.PagamentoEntity;
import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import br.com.lunacom.tools.util.DataUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PagamentoEntityBuilder {
    private PagamentoEntity pagamentoEntity;

    public static PagamentoEntityBuilder umPagamento() {
        FormaPagamentoEntity formaPagamentoEntity = FormaPagamentoEntity.builder()
                .tipo(FormaPagamentoEnum.AVISTA)
                .parcelas(5)
                .build();
        DescricaoEntity descricaoEntity = DescricaoEntity.builder()
                .valor("625.00")
                .dataHora(DataUtil.stringParaLocalDateTime("01/08/2022 10:30:00"))
                .estabelecimento("Colégio Pinho")
                .nsu("")
                .codigoAutorizacao("")
                .build();
        PagamentoEntity pagamentoEntity = PagamentoEntity.builder()
                .cartao("4465484984612312")
                .id(1064654654565451L)
                .descricao(descricaoEntity)
                .formaPagamento(formaPagamentoEntity)
                .build();

        return new PagamentoEntityBuilder(pagamentoEntity);
    }

    public PagamentoEntity agora() {
        return pagamentoEntity;
    }
}
