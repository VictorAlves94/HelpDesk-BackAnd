package com.victor.HelpDesk.domain.repository;

import com.victor.HelpDesk.domain.Chamado;
import com.victor.HelpDesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado,Integer> {
}
