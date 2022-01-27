package br.com.lunacom.tools.domain.entity;

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
@Table(name = "pagamento", uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class PagamentoEntity implements Serializable {
    @Id
    private Long id;
    private String cartao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "descricao_id")
    private DescricaoEntity descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamentoEntity formaPagamento;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PagamentoEntity entity = (PagamentoEntity) o;
        return Objects.equals(id, entity.id) && Objects.equals(cartao, entity.cartao) && Objects.equals(descricao, entity.descricao) && Objects.equals(formaPagamento, entity.formaPagamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartao, descricao, formaPagamento);
    }
}
