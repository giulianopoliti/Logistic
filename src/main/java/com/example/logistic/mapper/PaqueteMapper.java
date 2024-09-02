package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.Paquete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaqueteMapper {
    PaqueteDTO toDTO(Paquete paquete);
    Paquete toEntity(PaqueteDTO paqueteDTO);
}
