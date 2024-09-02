package com.example.logistic.mapper;

import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.roles.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
}
