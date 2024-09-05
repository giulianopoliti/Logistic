package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.Paquete;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaqueteMapper {

    // Mapea de Paquete a PaqueteDTO
    @Mapping(source = "cliente.id", target = "clienteId")
    PaqueteDTO toDTO(Paquete paquete);

    // Mapea de PaqueteDTO a Paquete
    @Mapping(source = "clienteId", target = "cliente.id")
    Paquete toEntity(PaqueteDTO paqueteDTO);
}

