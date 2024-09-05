package com.example.logistic.mapper;

import com.example.logistic.model.dtos.ViajeDTO;
import com.example.logistic.model.ruta.Viaje;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ViajeMapper {

    // Mapea de Viaje a ViajeDTO
    @Mapping(source = "ruta.id", target = "rutaId")
    @Mapping(source = "paquete.id", target = "paqueteId")
    @Mapping(source = "fechaCreacion", target = "date")
    ViajeDTO toDTO(Viaje viaje);

    // Mapea de ViajeDTO a Viaje
    @Mapping(source = "rutaId", target = "ruta.id")
    @Mapping(source = "paqueteId", target = "paquete.id")
    @Mapping(source = "date", target = "fechaCreacion")
    Viaje toEntity(ViajeDTO viajeDTO);
}
