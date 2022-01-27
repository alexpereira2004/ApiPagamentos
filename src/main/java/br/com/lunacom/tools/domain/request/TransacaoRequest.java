package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.validation.PagamentoUnico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
