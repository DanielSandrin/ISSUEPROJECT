package com.chamados.chamados.mapper;

import com.chamados.chamados.domain.clientes.ClienteRequestDTO;
import com.chamados.chamados.domain.clientes.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {
    Clientes toEntity(ClienteRequestDTO dto);

    void updateEntity(ClienteRequestDTO dto, @MappingTarget Clientes entity);
}
