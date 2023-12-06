package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.Pessoa;
import com.victor.HelpDesk.domain.dto.ClienteDto;
import com.victor.HelpDesk.domain.repository.ClienteRepository;
import com.victor.HelpDesk.domain.repository.PessoaRepository;
import com.victor.HelpDesk.serveces.exceptions.DataIntegrityViolationException;
import com.victor.HelpDesk.serveces.exceptions.ObjectnotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() ->new ObjectnotFoundException("Objeto nao encontrado! id:" + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDto objDto) {
        objDto.setId(null);
        validaPorCPFeEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }
    public Cliente uptade(Integer id,ClienteDto objDto) {
        objDto.setId(id);
        Cliente oldObj = findById(id);
        validaPorCPFeEmail(objDto);
        oldObj = new Cliente(objDto);
        return clienteRepository.save(oldObj);
    }
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if(obj.getChamados().size() > 0){
            throw new DataIntegrityViolationException("O tecnico possui ordens de serviço, e nao pode ser deletado!");
        }
        clienteRepository.deleteById(id);

    }

    private void validaPorCPFeEmail(ClienteDto objDto) {
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