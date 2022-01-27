package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoRequest {
    private FormaPagamentoEnum tipo;
    @NotNull(message = "Informe a quantidade de parcelas")
    private String parcelas;
}
