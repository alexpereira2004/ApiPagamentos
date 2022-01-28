package br.com.lunacom.tools.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResponse {
    private String cartao;
    private String id;
    private DescricaoResponse descricao;
    private FormaPagamentoResponse formaPagamento;
}
