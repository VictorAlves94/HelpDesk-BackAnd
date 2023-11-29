package com.victor.HelpDesk.domain.enums;

import lombok.Getter;

@Getter
public enum Perfil {
    Admin(0, "ROLE_ADMIN"),
    Cliente(1, "ROLE_CLIENTE"),
    Tecnico(2, "ROLE_TECNICO");
    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    public static Perfil toEnum(Integer cod){
        if (cod == null){
            return null;
        }
        for (Perfil x :Perfil.values()){
            if(cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Invalido");
    }

}
