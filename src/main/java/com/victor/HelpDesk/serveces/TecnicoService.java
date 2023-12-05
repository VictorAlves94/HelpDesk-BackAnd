package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.TecnicoDto;
import com.victor.HelpDesk.domain.repository.TecnicoRepository;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() ->new ObjectnotFoundException("Objeto nao encontrado! id:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        Tecnico newObj = new Tecnico(objDto);
        return tecnicoRepository.save(newObj);
    }
}
