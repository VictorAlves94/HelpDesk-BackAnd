package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Pessoa;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.TecnicoDto;
import com.victor.HelpDesk.domain.repository.PessoaRepository;
import com.victor.HelpDesk.domain.repository.TecnicoRepository;
import com.victor.HelpDesk.serveces.exceptions.DataIntegrityViolationException;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() ->new ObjectnotFoundException("Objeto nao encontrado! id:" + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto objDto) {
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        validaPorCPFeEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return tecnicoRepository.save(newObj);
    }
    public Tecnico update(Integer id, TecnicoDto objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        if(!objDto.getSenha().equals(oldObj.getSenha())){
            objDto.setSenha(encoder.encode(objDto.getSenha()));
        }
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

        // Validação se o CPF já está registrado
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }

        // Validação se o e-mail já está registrado
        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
        }
    }



}
