package br.com.lunacom.tools.domain.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransacaoResponse {
    private String cartao;
    private String id;
    private DescricaoResponse descricao;
    private FormaPagamentoResponse formaPagamento;
}
