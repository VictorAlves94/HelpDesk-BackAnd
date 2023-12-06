package com.victor.HelpDesk.resources;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.dto.ChamadoDto;
import com.victor.HelpDesk.domain.dto.ClienteDto;
import com.victor.HelpDesk.serveces.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value ="/chamados")
public class ChamadoResource {
    @Autowired
    private ChamadoService service;

    @GetMapping("{id}")
    public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDto(obj));
    }
    @GetMapping
    public ResponseEntity<List<ChamadoDto>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoDto> listDTO = list.stream().map(obj -> new ChamadoDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto objDto){
        Chamado obj = service.create(objDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }


}
