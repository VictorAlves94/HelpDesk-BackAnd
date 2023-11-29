package com.victor.HelpDesk.domain.enums;

import lombok.Getter;

@Getter
public enum Prioridade {
    Baixa(0, "Baixa"),
    Media(1, "Media"),
    Alta(2, "Alta");
    private Integer codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public static Prioridade toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Prioridade x : Prioridade.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Invalida");
    }

}
