package br.com.lunacom.tools.domain.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Data
public class PagamentoRequest {
    @Valid TransacaoRequest transacao;
}
