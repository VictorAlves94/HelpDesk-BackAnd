package com.victor.HelpDesk.domain.dto.chamados;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.enums.Prioridade;
import com.victor.HelpDesk.domain.enums.Status;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
public class ChamadoListarDto implements Serializable {
        private static final long serialVersionUid = 1L;

        private Integer id;
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataAbertura = LocalDate.now();
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataFechamento;
        private Prioridade prioridade;
        private Status status;
        private String titulo;
        private String observacoes;
        private Integer tecnico;
        private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

        public ChamadoListarDto(Chamado chamado){
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
