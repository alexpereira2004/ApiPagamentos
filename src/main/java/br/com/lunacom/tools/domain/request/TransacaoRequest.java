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
    @NotEmpty(message = "Informe o ID")
    @PagamentoUnico
    private String id;
    @Valid
    @NotNull(message = "Objeto Descricao deve ser informado")
    private DescricaoRequest descricao;
    @Valid
    @NotNull(message = "Objeto FormaPagamento deve ser informado")
    private FormaPagamentoRequest formaPagamento;
}
