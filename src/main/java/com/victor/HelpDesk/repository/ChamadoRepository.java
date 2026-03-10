package com.victor.HelpDesk.repository;

import com.victor.HelpDesk.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {
}
