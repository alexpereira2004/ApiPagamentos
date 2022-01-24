package br.com.lunacom.tools.domain.estorno;

import br.com.lunacom.tools.domain.FormaPagamento;
import br.com.lunacom.tools.domain.Transacao;
import lombok.Data;

@Data
public class EstornoResponse {
    Transacao transacao;
    FormaPagamento formaPagamento;
}
