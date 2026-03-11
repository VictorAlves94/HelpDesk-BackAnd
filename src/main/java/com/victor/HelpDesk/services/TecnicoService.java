package com.victor.HelpDesk.services;

import com.victor.HelpDesk.entity.Pessoa;
import com.victor.HelpDesk.entity.Tecnico;
import com.victor.HelpDesk.dto.TecnicoDto;
import com.victor.HelpDesk.repository.PessoaRepository;
import com.victor.HelpDesk.repository.TecnicoRepository;
import com.victor.HelpDesk.exceptions.DataIntegrityViolationException;
import com.victor.HelpDesk.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder encoder;

    public TecnicoService(
            TecnicoRepository tecnicoRepository,
            PessoaRepository pessoaRepository,
            BCryptPasswordEncoder encoder) {

        this.tecnicoRepository = tecnicoRepository;
        this.pessoaRepository = pessoaRepository;
        this.encoder = encoder;
    }

    public Tecnico findById(Integer id) {

        return tecnicoRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! id:" + id));
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

        validaPorCPFeEmail(objDto);

        if (!objDto.getSenha().equals(oldObj.getSenha())) {
            objDto.setSenha(encoder.encode(objDto.getSenha()));
        }

        oldObj.setNome(objDto.getNome());
        oldObj.setEmail(objDto.getEmail());
        oldObj.setCpf(objDto.getCpf());
        oldObj.setSenha(objDto.getSenha());

        return tecnicoRepository.save(oldObj);
    }

    public void delete(Integer id) {

        Tecnico obj = findById(id);

        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException(
                    "O técnico possui ordens de serviço e não pode ser deletado!");
        }

        tecnicoRepository.deleteById(id);
    }

    private void validaPorCPFeEmail(TecnicoDto objDto) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && !obj.get().getId().equals(objDto.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && !obj.get().getId().equals(objDto.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
        }
    }
}