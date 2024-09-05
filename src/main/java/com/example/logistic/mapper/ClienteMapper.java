package com.example.logistic.mapper;

import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.roles.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(source = "tenant.id", target = "tenantId")
    ClienteDTO toDTO(Cliente cliente);
    @Mapping(source = "tenantId", target = "tenant.id")
    Cliente toEntity(ClienteDTO clienteDTO);
}
