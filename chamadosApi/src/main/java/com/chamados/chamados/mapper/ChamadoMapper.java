package com.chamados.chamados.mapper;

import com.chamados.chamados.domain.chamados.Chamado;
import com.chamados.chamados.domain.chamados.ChamadoRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ChamadoMapper {
    Chamado toEntity(ChamadoRequestDTO dto);

    void updateEntity(ChamadoRequestDTO dto, @MappingTarget Chamado entity);
}
