package com.victor.HelpDesk.entity;

import com.fasterxml.jackson.annotation.*;
import com.victor.HelpDesk.enums.Prioridade;
import com.victor.HelpDesk.enums.Status;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Chamado implements Serializable {
    private static final long serialVersionUID  = 1L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    private Integer prioridade;
    private Integer status;

    private String titulo;
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Cliente cliente;

    public Chamado() {}

    public Chamado(Integer id, Prioridade prioridade, Status status,
                   String titulo, String observacoes,
                   Tecnico tecnico, Cliente cliente) {

        this.id = id;
        this.prioridade = prioridade.getCodigo();
        this.status = status.getCodigo();
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Prioridade getPrioridade() {
        return Prioridade.toEnum(this.prioridade);
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade.getCodigo();
    }

    public Status getStatus() {
        return Status.toEnum(this.status);
    }

    public void setStatus(Status status) {
        this.status = status.getCodigo();
    }
}