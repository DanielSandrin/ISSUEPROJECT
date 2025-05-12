package com.chamados.chamados.mapper;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.imagens.Imagem;
import com.chamados.chamados.domain.imagens.ImagemPostResponseDTO;
import com.chamados.chamados.domain.imagens.ImagemResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.awt.*;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImagemMapper {
    Imagem toEntity(String nome);

    List<ImagemResponseDTO> ToDTOList(List<Imagem> imagens);

    ImagemPostResponseDTO toPostDTOResponse(Imagem imagem);
}
