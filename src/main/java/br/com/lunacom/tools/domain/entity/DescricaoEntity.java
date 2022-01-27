package br.com.lunacom.tools.domain.entity;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "descricao")
public class DescricaoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String valor;
    LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescricaoEntity that = (DescricaoEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(valor, that.valor) && Objects.equals(dataHora, that.dataHora) && Objects.equals(estabelecimento, that.estabelecimento) && Objects.equals(nsu, that.nsu) && Objects.equals(codigoAutorizacao, that.codigoAutorizacao) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, valor, dataHora, estabelecimento, nsu, codigoAutorizacao, status);
    }
}
