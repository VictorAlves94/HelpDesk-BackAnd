package com.victor.HelpDesk.repository;

import com.victor.HelpDesk.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico,Integer> {
}