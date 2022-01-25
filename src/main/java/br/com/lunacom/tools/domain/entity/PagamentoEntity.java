package br.com.lunacom.tools.domain.entity;

import br.com.lunacom.tools.domain.request.DescricaoRequest;
import br.com.lunacom.tools.domain.request.FormaPagamentoRequest;
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
@Table(name = "pagamento")
public class PagamentoEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cartao;

    @OneToOne
    @JoinColumn(name = "descricao_id")
    private DescricaoEntity descricao;

    @OneToOne
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamentoEntity formaPagamento;
}
