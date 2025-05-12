package com.chamados.chamados.domain.chamados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record ChamadoRequestDTO(UUID id,
                                @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
                                String descricao,
                                int prioridade,
                                Date dataAbertura,
                                Date dataFinalizacao,
                                UUID clienteId) {
}
