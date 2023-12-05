package com.victor.HelpDesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.victor.HelpDesk.domain.dto.TecnicoDto;
import com.victor.HelpDesk.domain.enums.Perfil;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUid = 1L;


    @OneToMany(mappedBy = "tecnico",fetch = FetchType.LAZY)
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.Cliente);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.Cliente);
    }

    public Tecnico(TecnicoDto obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfils = obj.getPerfils().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = obj.getDataCriacao();
    }
    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
