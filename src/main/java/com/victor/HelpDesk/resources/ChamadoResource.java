package com.victor.HelpDesk.resources;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.dto.ChamadoDto;
import com.victor.HelpDesk.serveces.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    

}
