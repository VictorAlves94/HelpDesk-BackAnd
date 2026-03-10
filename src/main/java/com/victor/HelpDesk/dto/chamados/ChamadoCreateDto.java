package com.victor.HelpDesk.dto.chamados;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.HelpDesk.entity.Chamado;
import com.victor.HelpDesk.enums.Prioridade;
import com.victor.HelpDesk.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter

public class ChamadoCreateDto implements Serializable {
        private static final long serialVersionUid = 1L;

        private Integer id;
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataAbertura = LocalDate.now();
        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataFechamento;
        @NotNull(message = "O campo PRIORIDADE é requerido")
        private Prioridade prioridade;
        @NotNull(message = "O campo STATUS é requerido")
        private Status status;
        @NotNull(message = "O campo TITULO é requerido")
        private String titulo;
        @NotNull(message = "O campo OBSERVAÇÕES é requerido")
        private String observacoes;
        @NotNull(message = "O campo TECNICO é requerido")
        private Integer tecnico;
        @NotNull(message = "O campo CLIENTE é requerido")
        private Integer cliente;


        public ChamadoCreateDto(Chamado chamado) {
            this.id = chamado.getId();
            this.dataAbertura = chamado.getDataAbertura();
            this.dataFechamento = chamado.getDataFechamento();
            this.prioridade = chamado.getPrioridade();
            this.status = chamado.getStatus();
            this.titulo = chamado.getTitulo();
            this.observacoes = chamado.getObservacoes();
            this.tecnico = chamado.getTecnico().getId();
            this.cliente = chamado.getCliente().getId();
        }

    }
    
