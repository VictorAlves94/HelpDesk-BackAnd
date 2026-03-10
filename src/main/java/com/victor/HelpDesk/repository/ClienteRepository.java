package com.victor.HelpDesk.repository;

import com.victor.HelpDesk.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}