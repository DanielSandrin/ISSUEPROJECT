package com.chamados.chamados.domain.imagens;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImagemResponseDTO {
    private UUID id;
    private String nome;
    private String urlImg;
}
