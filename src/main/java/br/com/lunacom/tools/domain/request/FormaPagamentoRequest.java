package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FormaPagamentoRequest {
    private FormaPagamentoEnum tipo;
    @NotNull(message = "Informe a quantidade de parcelas")
    private String parcelas;
}
