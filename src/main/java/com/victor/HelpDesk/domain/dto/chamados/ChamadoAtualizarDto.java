package com.victor.HelpDesk.domain.dto.chamados;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.enums.Prioridade;
import com.victor.HelpDesk.domain.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ChamadoAtualizarDto implements Serializable {
    private static final long serialVersionUid = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull
    private Prioridade prioridade;
    @NotNull
    private Status status;
    @NotNull
    private String titulo;
    private String observacoes;
    @NotNull
    private Integer tecnico;
    @NotNull
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;


    public ChamadoAtualizarDto(Chamado chamado) {
        this.id = chamado.getId();
        this.dataAbertura = chamado.getDataAbertura();
        this.dataFechamento = chamado.getDataFechamento();
        this.prioridade = chamado.getPrioridade();
        this.status = chamado.getStatus();
        this.titulo = chamado.getTitulo();
        this.observacoes = chamado.getObservacoes();
        this.tecnico = chamado.getTecnico().getId();
        this.cliente = chamado.getCliente().getId();
        this.nomeTecnico = chamado.getTecnico().getNome();
        this.nomeCliente = chamado.getCliente().getNome();
    }

}