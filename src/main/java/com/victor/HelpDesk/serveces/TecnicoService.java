package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Pessoa;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.TecnicoDto;
import com.victor.HelpDesk.domain.repository.PessoaRepository;
import com.victor.HelpDesk.domain.repository.TecnicoRepository;
import com.victor.HelpDesk.serveces.exceptions.DataIntegrityViolationException;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() ->new ObjectnotFoundException("Objeto nao encontrado! id:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        validaPorCPFeEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return tecnicoRepository.save(newObj);
    }
    public Tecnico uptade(Integer id, TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCPFeEmail(objDto);
        oldObj = new Tecnico(objDto);
        return tecnicoRepository.save(oldObj);
    }
    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O tecnico possui ordens de serviço, e nao pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);

    }

    private void validaPorCPFeEmail(TecnicoDto objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
        throw new DataIntegrityViolationException("Cpf já cadastrado no sistema.");
        }
        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
        }

    }



}
