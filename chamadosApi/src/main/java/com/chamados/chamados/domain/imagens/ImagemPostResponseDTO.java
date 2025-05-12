package com.chamados.chamados.domain.imagens;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImagemPostResponseDTO {
    private UUID id;
    private String nome;
}
