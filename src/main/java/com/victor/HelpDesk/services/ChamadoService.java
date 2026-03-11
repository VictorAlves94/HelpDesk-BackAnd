package com.victor.HelpDesk.services;

import com.victor.HelpDesk.dto.chamados.ChamadoCreateDto;
import com.victor.HelpDesk.entity.Chamado;
import com.victor.HelpDesk.entity.Cliente;
import com.victor.HelpDesk.entity.Tecnico;
import com.victor.HelpDesk.dto.chamados.ChamadoAtualizarDto;
import com.victor.HelpDesk.enums.Prioridade;
import com.victor.HelpDesk.enums.Status;
import com.victor.HelpDesk.repository.ChamadoRepository;
import com.victor.HelpDesk.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@Service
public class ChamadoService {
    private final ChamadoRepository chamadoRepository;
    private final TecnicoService tecnicoService;
    private final ClienteService clienteService;

    public ChamadoService(
            ChamadoRepository chamadoRepository,
            TecnicoService tecnicoService,
            ClienteService clienteService) {

        this.chamadoRepository = chamadoRepository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    public Chamado findById(Integer id) {
        return chamadoRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado create(@Valid ChamadoCreateDto dto) {
        return chamadoRepository.save(createChamado(dto));
    }

    public Chamado update(Integer id, @Valid ChamadoAtualizarDto dto) {

        Chamado chamado = findById(id);
        updateChamadoDto(chamado, dto);

        return chamadoRepository.save(chamado);
    }

    private Chamado createChamado(ChamadoCreateDto dto) {

        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        Chamado chamado = new Chamado();

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade().getCodigo()));
        chamado.setStatus(Status.toEnum(dto.getStatus().getCodigo()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());

        return chamado;
    }

    private void updateChamadoDto(Chamado chamado, ChamadoAtualizarDto dto) {

        Tecnico tecnico = tecnicoService.findById(dto.getTecnico());
        Cliente cliente = clienteService.findById(dto.getCliente());

        if (dto.getStatus().getCodigo() == 2 && chamado.getDataFechamento() == null) {
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade().getCodigo()));
        chamado.setStatus(Status.toEnum(dto.getStatus().getCodigo()));
        chamado.setTitulo(dto.getTitulo());
        chamado.setObservacoes(dto.getObservacoes());
    }
}