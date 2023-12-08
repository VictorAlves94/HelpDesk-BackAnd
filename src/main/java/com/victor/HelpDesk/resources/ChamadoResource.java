package com.victor.HelpDesk.resources;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.dto.chamados.ChamadoAtualizarDto;
import com.victor.HelpDesk.domain.dto.chamados.ChamadoCeateDto;
import com.victor.HelpDesk.domain.dto.chamados.ChamadoListarDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoListarDto> findById(@PathVariable Integer id){
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoListarDto(obj));
    }
    @GetMapping
    public ResponseEntity<List<ChamadoListarDto>> findAll(){
        List<Chamado> list = service.findAll();
        List<ChamadoListarDto> listDTO = list.stream().map(obj -> new ChamadoListarDto(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
    @PostMapping
    public ResponseEntity<ChamadoCeateDto> create(@Valid @RequestBody ChamadoCeateDto objDto){
        Chamado obj = service.create(objDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
    @PutMapping("/{id}")
    public  ResponseEntity<ChamadoAtualizarDto> update(@PathVariable Integer id, @ Valid @RequestBody ChamadoAtualizarDto objDto){
        Chamado newObj = service.update(id,objDto);
        return ResponseEntity.ok().body(new ChamadoAtualizarDto(newObj));
    }


}
