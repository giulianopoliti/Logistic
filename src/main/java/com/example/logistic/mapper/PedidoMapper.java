package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.ruta.paquete.Pedido;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    // Mapea de Paquete a PaqueteDTO
    @Mapping(source = "cliente.id", target = "clienteId")
    PedidoDTO toDTO(Pedido pedido);

    // Mapea de PaqueteDTO a Paquete
    @Mapping(source = "clienteId", target = "cliente.id")
    Pedido toEntity(PedidoDTO pedidoDTO);
}

