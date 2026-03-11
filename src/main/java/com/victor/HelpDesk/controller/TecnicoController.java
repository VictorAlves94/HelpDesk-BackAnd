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

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService service) {
        this.tecnicoService = service;
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDto(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDto> listDTO = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

        }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDto> create(@Valid @RequestBody TecnicoDto objDto){
    Tecnico newObj =tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
    return ResponseEntity.created(uri).body(new TecnicoDto(newObj));
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDto> update(@PathVariable Integer id,@Valid @RequestBody TecnicoDto objDto){
        Tecnico obj = tecnicoService.update(id,objDto);
        return ResponseEntity.ok().body(new TecnicoDto(obj));

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value ="/{id}")
    public ResponseEntity<TecnicoDto> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();

    }


}
