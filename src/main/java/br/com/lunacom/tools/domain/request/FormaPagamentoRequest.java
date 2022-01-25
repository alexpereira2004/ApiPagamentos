package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.Data;

@Data
public class FormaPagamentoRequest {
    FormaPagamentoEnum tipo;
    int parcelas;
}
