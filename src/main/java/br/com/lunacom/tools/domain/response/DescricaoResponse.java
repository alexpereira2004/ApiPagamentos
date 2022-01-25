package br.com.lunacom.tools.domain.response;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import lombok.Data;

@Data
public class DescricaoResponse {
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;
}
