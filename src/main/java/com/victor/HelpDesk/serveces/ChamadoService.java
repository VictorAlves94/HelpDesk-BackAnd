package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.chamados.ChamadoAtualizarDto;
import com.victor.HelpDesk.domain.dto.chamados.ChamadoCeateDto;
import com.victor.HelpDesk.domain.enums.Prioridade;
import com.victor.HelpDesk.domain.enums.Status;
import com.victor.HelpDesk.domain.repository.ChamadoRepository;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado ! ID:" + id));
    }

    public List<Chamado> findAll() {
        List<Chamado> all = chamadoRepository.findAll();
        return all;
    }

    public Chamado create(ChamadoCeateDto objDto) {
        return chamadoRepository.save(createChamado(objDto));
    }

    public Chamado update(Integer id, @Valid ChamadoAtualizarDto objDto) {
        objDto.setId(id);
        Chamado oldObj = findById(id);

        updateChamadoDto(oldObj, objDto);

        return chamadoRepository.save(oldObj);
    }

    private Chamado createChamado(ChamadoCeateDto obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if(obj.getId() != null) {
            chamado.setId(obj.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCodigo()));
        chamado.setStatus(Status.toEnum(obj.getStatus().getCodigo()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;

    }

    private void updateChamadoDto(Chamado chamado, ChamadoAtualizarDto obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        if (obj.getStatus().getCodigo() == 2 && chamado.getDataFechamento() == null) {

            chamado.setDataFechamento(LocalDate.now());
        } else if (obj.getStatus().getCodigo() != 2) {
            // Se o status não for 2, certifique-se de que a data de fechamento esteja nula
            chamado.setDataFechamento(null);
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade().getCodigo()));
        chamado.setStatus(Status.toEnum(obj.getStatus().getCodigo()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
    }
}

