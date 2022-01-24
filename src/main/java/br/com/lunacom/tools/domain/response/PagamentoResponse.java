package br.com.lunacom.tools.domain.response;

import br.com.lunacom.tools.domain.FormaPagamento;
import br.com.lunacom.tools.domain.Transacao;
import lombok.Data;

@Data
public class PagamentoResponse {
    Transacao transacao;
    FormaPagamento formaPagamento;
}
