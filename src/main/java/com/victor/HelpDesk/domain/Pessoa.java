package com.victor.HelpDesk.domain;

import com.victor.HelpDesk.domain.enums.Perfil;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;



@Getter
@Setter
@EqualsAndHashCode
public abstract class Pessoa {
    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;

    protected String senha;

    protected Set<Integer> perfils = new HashSet<>();
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa() {
        super();
        addPerfil(Perfil.Cliente);
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.Cliente);
    }

    public Set<Perfil> getPerfils() {
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfils.add(perfil.getCodigo()) ;
    }
}
