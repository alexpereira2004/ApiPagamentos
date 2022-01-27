package br.com.lunacom.tools.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagamentoRequest {
    @Valid TransacaoRequest transacao;
}
