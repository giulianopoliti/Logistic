package com.example.logistic.mapper;

import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.ruta.Ruta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", uses = PedidoMapper.class)
public interface RutaMapper {
    RutaMapper INSTANCE = Mappers.getMapper(RutaMapper.class);

    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "pedidos", target = "pedidoDTOS")
    RutaDTO toDTO(Ruta ruta);

    @Mapping(source = "driverId", target = "driver.id")
    @Mapping(source = "pedidoDTOS", target = "pedidos")
    Ruta toEntity(RutaDTO rutaDTO);
}
