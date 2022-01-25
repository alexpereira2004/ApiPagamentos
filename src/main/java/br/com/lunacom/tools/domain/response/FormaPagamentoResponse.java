package br.com.lunacom.tools.domain.response;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.Data;

@Data
public class FormaPagamentoResponse {
    FormaPagamentoEnum tipo;
    int parcelas;
}
