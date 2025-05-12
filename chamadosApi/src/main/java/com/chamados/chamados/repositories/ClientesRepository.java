package com.chamados.chamados.repositories;

import com.chamados.chamados.domain.clientes.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Clientes, UUID> {
}
