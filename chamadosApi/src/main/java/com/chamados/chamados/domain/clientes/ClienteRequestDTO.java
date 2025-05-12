package com.chamados.chamados.domain.clientes;

import java.util.UUID;

public record ClienteRequestDTO(UUID id, String name, String cidade, String uf) {
}
