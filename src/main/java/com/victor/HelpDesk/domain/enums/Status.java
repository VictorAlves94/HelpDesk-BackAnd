package com.victor.HelpDesk.domain.enums;

import lombok.Getter;

@Getter
public enum Status {
    Aberto(0, "Aberto"),
    Andamento(1, "Andamento"),
    Encerrado(2, "Encerrado");
    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public static Status toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Status x : Status.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status Invalido");
    }

}
