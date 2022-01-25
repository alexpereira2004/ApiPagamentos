package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DescricaoRequest {
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;
}
