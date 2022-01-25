package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.validation.PagamentoUnico;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TransacaoRequest {
    @NotEmpty(message = "Informe o número do cartão")
    private String cartao;
    @PagamentoUnico
    @NotNull(message = "Informe o ID")
    private String id;
    @Valid
    private DescricaoRequest descricao;
    @Valid
    private FormaPagamentoRequest formaPagamento;
}
