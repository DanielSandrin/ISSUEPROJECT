package com.chamados.chamados.repositories;

import com.chamados.chamados.domain.chamados.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChamadoRepository extends JpaRepository<Chamado, UUID> {
}
