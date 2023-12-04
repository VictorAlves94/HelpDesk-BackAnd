package com.victor.HelpDesk.resources;

import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.dto.TecnicoDto;
import com.victor.HelpDesk.serveces.TecnicoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/tecnicos")
public class TecnicoResource {
    @Autowired
    private TecnicoService service;
    @GetMapping(value ="/{id}")
    public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id){
    Tecnico obj = service.findById(id);
    return ResponseEntity.ok().body(new TecnicoDto(obj));

}
}
