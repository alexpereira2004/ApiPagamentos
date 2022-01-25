package br.com.lunacom.tools.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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
}
