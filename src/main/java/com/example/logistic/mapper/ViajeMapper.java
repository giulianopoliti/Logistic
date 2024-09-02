package com.example.logistic.mapper;

import com.example.logistic.model.dtos.ViajeDTO;
import com.example.logistic.model.ruta.Viaje;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ViajeMapper {
    ViajeDTO toDTO(Viaje viaje);
    Viaje toEntity(ViajeDTO viajeDTO);
}
