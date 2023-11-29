package com.victor.HelpDesk.domain;

import com.victor.HelpDesk.domain.enums.Perfil;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
@Getter
@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUid = 1L;
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.Cliente);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.Cliente);
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
