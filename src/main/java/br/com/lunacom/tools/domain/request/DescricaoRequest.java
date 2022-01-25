package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class DescricaoRequest {
    @NotEmpty(message = "Informe o valor")
    private String valor;
    @NotEmpty(message = "Informe a data e hora")
    @Pattern(regexp="^[0-3]?[0-9]/[0-3]?[0-9]/[0-9]{4} [0-2]?[0-9]{1}:[0-9]{2}:[0-9]{2}$",
            message = "O campo data e hora deve estar no formato dd/mm/YYYY HH:mm:ss")
    private String dataHora;
    @NotEmpty(message = "Informe o estabelecimento")
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;
}
