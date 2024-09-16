package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.PedidoMeliDTO;

import com.example.logistic.model.dtos.PedidoParticularDTO;
import com.example.logistic.model.dtos.TipoPedido;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.model.ruta.paquete.PedidoMeli;
import com.example.logistic.model.ruta.paquete.PedidoParticular;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public PedidoDTO toDTO (Pedido pedido) {
        PedidoDTO pedidoDTO;
        if (pedido.getClass() == PedidoMeli.class) {
            pedidoDTO = new PedidoMeliDTO();
            ((PedidoMeliDTO) pedidoDTO).setOrderId(((PedidoMeli) pedido).getOrderId());
            ((PedidoMeliDTO) pedidoDTO).setSellerId(((PedidoMeli) pedido).getSellerId());
        } else {
            pedidoDTO = new PedidoParticularDTO();
        }
        pedidoDTO.setId(pedidoDTO.getId());
        pedidoDTO.setContenido(pedido.getContenido());
        pedidoDTO.setVendedorId(pedido.getVendedor().getAuthId());
        pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
        pedidoDTO.setUbicacionActual(pedido.getUbicacionActual());
        pedidoDTO.setUbicacionEntrega(pedido.getUbicacionEntrega());
        pedidoDTO.setTenantId(pedido.getTenant().getId());
        if (pedidoDTO.getRutaId() != null) {
            pedidoDTO.setRutaId(pedido.getRuta().getId());
        }
        if (pedidoDTO.getDriverId() != null) {
            pedidoDTO.setDriverId(pedido.getDriver().getAuthId());
        }
        pedidoDTO.setCompradorName(pedido.getCompradorName());
        if (pedidoDTO.getObservacion() != null) {
            pedidoDTO.setObservacion(pedido.getObservacion());
        }
        return pedidoDTO;
    }
}
