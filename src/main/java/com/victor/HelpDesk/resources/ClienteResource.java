package com.victor.HelpDesk.resources;

import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.dto.ClienteDto;
import com.victor.HelpDesk.serveces.ClienteService;
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
public class ClienteResource {
    @Autowired
    private ClienteService service;

    @GetMapping(value ="/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDto> listDTO = list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

        }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        Cliente newObj = service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id,@Valid @RequestBody ClienteDto objDto){
        Cliente obj = service.update(id,objDto);
        return ResponseEntity.ok().body(new ClienteDto(obj));

    }
    @DeleteMapping(value ="/{id}")
    public ResponseEntity<ClienteDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
