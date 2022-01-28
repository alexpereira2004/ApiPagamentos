package br.com.lunacom.tools.domain.response;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoResponse {
    FormaPagamentoEnum tipo;
    int parcelas;
}
