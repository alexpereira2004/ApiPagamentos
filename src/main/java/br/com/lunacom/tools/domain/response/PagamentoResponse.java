package br.com.lunacom.tools.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PagamentoResponse {
    TransacaoResponse transacao;
}
