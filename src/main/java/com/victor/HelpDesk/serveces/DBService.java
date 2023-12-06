package com.victor.HelpDesk.serveces;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Cliente;
import com.victor.HelpDesk.domain.Tecnico;
import com.victor.HelpDesk.domain.enums.Perfil;
import com.victor.HelpDesk.domain.enums.Prioridade;
import com.victor.HelpDesk.domain.enums.Status;
import com.victor.HelpDesk.domain.repository.ChamadoRepository;
import com.victor.HelpDesk.domain.repository.ClienteRepository;
import com.victor.HelpDesk.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;
 public void InstanciaDB(){
     Tecnico tec1 = new Tecnico(null,"Valdir cezar", "453.694.970-44", "valdir@gmail.com", "123");
     tec1.addPerfil(Perfil.Admin);

     Cliente cli1 =new Cliente(null,"Linus Torvalds", "511.658.920-06","linus@gmail.com", "123");

     Chamado c1 =new Chamado(null, Prioridade.Media, Status.Andamento,"Chamado 1","primeiro chamado", tec1,cli1);

     tecnicoRepository.saveAll(Arrays.asList(tec1));
     clienteRepository.saveAll(Arrays.asList(cli1));
     chamadoRepository.saveAll(Arrays.asList(c1));
 }
}
