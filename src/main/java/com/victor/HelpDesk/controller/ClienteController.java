package com.victor.HelpDesk.controller;

import com.victor.HelpDesk.entity.Cliente;
import com.victor.HelpDesk.dto.ClienteDto;
import com.victor.HelpDesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
        Cliente obj = clienteService
                .findById(id);
        return ResponseEntity.ok().body(new ClienteDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> list = clienteService.findAll();
        List<ClienteDto> listDTO = list.stream()
                .map(ClienteDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

        }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        Cliente newObj = clienteService.create(objDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newObj
                        .getId()).toUri();
    return ResponseEntity.created(uri).body(new ClienteDto(newObj));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id,@Valid @RequestBody ClienteDto objDto){
        Cliente obj = clienteService
                .update(id,objDto);
        return ResponseEntity.ok().body(new ClienteDto(obj));

    }
    @DeleteMapping(value ="/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        clienteService
                .delete(id);
        return ResponseEntity.noContent().build();

    }


}
