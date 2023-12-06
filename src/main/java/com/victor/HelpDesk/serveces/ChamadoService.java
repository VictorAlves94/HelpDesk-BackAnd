package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Chamado;
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

    public Chamado findById(Integer id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectnotFoundException("Objeto não encontrado ! ID:" + id));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
