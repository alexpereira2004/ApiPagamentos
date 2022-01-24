package br.com.lunacom.tools.domain;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.Data;

@Data
public class FormaPagamento {
    FormaPagamentoEnum tipo;
    int parcelas;
}
