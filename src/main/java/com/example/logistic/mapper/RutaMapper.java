package com.example.logistic.mapper;

import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.ruta.Ruta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {ViajeMapper.class})  // ViajeMapper se usa para mapear Viaje a ViajeDTO
public interface RutaMapper {
    // Mapea de Ruta a RutaDTO
    @Mapping(source = "driver.id", target = "driverId")
    RutaDTO toDTO(Ruta ruta);

    // Mapea de RutaDTO a Ruta
    @Mapping(source = "driverId", target = "driver.id")
    Ruta toEntity(RutaDTO rutaDTO);
}
