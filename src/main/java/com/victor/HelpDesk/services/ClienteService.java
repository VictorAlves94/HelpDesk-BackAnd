package com.victor.HelpDesk.services;

import com.victor.HelpDesk.entity.Cliente;
import com.victor.HelpDesk.entity.Pessoa;
import com.victor.HelpDesk.dto.ClienteDto;
import com.victor.HelpDesk.repository.ClienteRepository;
import com.victor.HelpDesk.repository.PessoaRepository;
import com.victor.HelpDesk.exceptions.DataIntegrityViolationException;
import com.victor.HelpDesk.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder encoder;

    public ClienteService(
            ClienteRepository clienteRepository,
            PessoaRepository pessoaRepository,
            BCryptPasswordEncoder encoder) {

        this.clienteRepository = clienteRepository;
        this.pessoaRepository = pessoaRepository;
        this.encoder = encoder;
    }

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Objeto não encontrado! id: " + id));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDto objDto) {

        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));

        validaPorCPFeEmail(objDto);

        Cliente newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDto objDto) {

        objDto.setId(id);

        Cliente oldObj = findById(id);

        validaPorCPFeEmail(objDto);

        if (!objDto.getSenha().equals(oldObj.getSenha())) {
            objDto.setSenha(encoder.encode(objDto.getSenha()));
        }

        oldObj.setNome(objDto.getNome());
        oldObj.setEmail(objDto.getEmail());
        oldObj.setCpf(objDto.getCpf());
        oldObj.setSenha(objDto.getSenha());

        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {

        Cliente obj = findById(id);

        if (!obj.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException(
                    "O cliente possui ordens de serviço e não pode ser deletado!");
        }

        clienteRepository.deleteById(id);
    }

    private void validaPorCPFeEmail(ClienteDto objDto) {

        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema.");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema.");
        }
    }
}