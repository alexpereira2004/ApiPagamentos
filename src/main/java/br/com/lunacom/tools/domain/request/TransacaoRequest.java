package br.com.lunacom.tools.domain.request;

import lombok.Data;

@Data
public class TransacaoRequest {
    private String cartao;
    private String id;
    private DescricaoRequest descricao;
    private FormaPagamentoRequest formaPagamento;
}
