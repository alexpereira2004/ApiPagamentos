package br.com.lunacom.tools.domain.request;

import lombok.Data;

import javax.validation.Valid;

@Data
public class PagamentoRequest {
    @Valid TransacaoRequest transacao;
}
