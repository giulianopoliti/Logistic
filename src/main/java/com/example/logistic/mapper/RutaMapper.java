package com.example.logistic.mapper;

import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.ruta.Ruta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RutaMapper {
    RutaDTO toDTO(Ruta ruta);
    Ruta toEntity(RutaDTO rutaDTO);
}
