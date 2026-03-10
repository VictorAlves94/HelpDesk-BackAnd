package com.victor.HelpDesk.controller;

import com.victor.HelpDesk.entity.Tecnico;
import com.victor.HelpDesk.dto.TecnicoDto;
import com.victor.HelpDesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/tecnicos")
public class TecnicoController {
    @Autowired
    private TecnicoService service;

    @GetMapping(value ="/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDto> listDTO = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

        }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto){
    Tecnico newObj =service.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(uri).build();
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDto objDto){
        Tecnico obj = service.update(id,objDto);
        return ResponseEntity.ok().body(new TecnicoDto(obj));

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }


}
