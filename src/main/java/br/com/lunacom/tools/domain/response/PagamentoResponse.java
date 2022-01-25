package br.com.lunacom.tools.domain.response;

import br.com.lunacom.tools.domain.request.TransacaoRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PagamentoResponse {
    TransacaoResponse transacao;
}
