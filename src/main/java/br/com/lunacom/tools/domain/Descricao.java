package br.com.lunacom.tools.domain;

import br.com.lunacom.tools.domain.enumeration.StatusEnum;

import java.time.LocalDateTime;

public class Descricao {
    private String valor;
    LocalDateTime dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    private StatusEnum status;
}
