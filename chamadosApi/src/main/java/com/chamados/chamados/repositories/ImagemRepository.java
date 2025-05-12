package com.chamados.chamados.repositories;

import com.chamados.chamados.domain.imagens.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImagemRepository extends JpaRepository<Imagem, UUID> {

    List<Imagem> findByChamadoId(UUID chamadoId);
}
