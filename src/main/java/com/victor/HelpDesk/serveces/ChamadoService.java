package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.ChamadoDto;
import com.victor.HelpDesk.domain.enums.Prioridade;
import com.victor.HelpDesk.domain.enums.Status;
import com.victor.HelpDesk.domain.repository.ChamadoRepository;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return chamadoRepository.findAll();
    }

    public Chamado create(ChamadoDto objDto) {

        return chamadoRepository.save(newChamado(objDto));
    }
    private Chamado newChamado(ChamadoDto obj){
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico().getId());
        Cliente cliente = clienteService.findById(obj.getCliente().getId());

        Chamado chamado = new Chamado();
        if (obj.getId() != null ){
            chamado.setId(obj.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());


        return chamado;
    }
}
