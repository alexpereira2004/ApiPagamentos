package br.com.lunacom.tools.domain.request;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
