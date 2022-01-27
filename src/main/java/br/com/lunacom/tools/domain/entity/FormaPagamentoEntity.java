package br.com.lunacom.tools.domain.entity;

import br.com.lunacom.tools.domain.enumeration.FormaPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "forma_pagamento")
public class FormaPagamentoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    FormaPagamentoEnum tipo;
    int parcelas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FormaPagamentoEntity that = (FormaPagamentoEntity) o;
        return parcelas == that.parcelas && Objects.equals(id, that.id) && tipo == that.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tipo, parcelas);
    }
}
