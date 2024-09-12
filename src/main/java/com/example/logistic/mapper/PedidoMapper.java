package com.example.logistic.mapper;

import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.PedidoMeliDTO;

import com.example.logistic.model.dtos.TipoPedido;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.model.ruta.paquete.PedidoMeli;
import com.example.logistic.model.ruta.paquete.PedidoParticular;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public PedidoDTO toDTO (Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        if (pedido.getClass() == PedidoMeli.class) {
            pedidoDTO = new PedidoMeliDTO();
            ((PedidoMeliDTO) pedidoDTO).setOrderId(((PedidoMeli) pedido).getOrderId());
            ((PedidoMeliDTO) pedidoDTO).setSellerId(((PedidoMeli) pedido).getSellerId());
        }
        pedidoDTO.setId(pedidoDTO.getId());
        pedidoDTO.setContenido(pedido.getContenido());
        pedidoDTO.setVendedorId(pedido.getVendedor().getId());
        pedidoDTO.setFechaCreacion(pedido.getFechaCreacion());
        pedidoDTO.setUbicacionActual(pedido.getUbicacionActual());
        pedidoDTO.setUbicacionEntrega(pedido.getUbicacionEntrega());
        pedidoDTO.setTenantId(pedido.getTenant().getId());
        pedidoDTO.setRutaId(pedido.getRuta().getId());
        pedidoDTO.setDriverId(pedido.getDriver().getId());
        pedidoDTO.setCompradorName(pedido.getCompradorName());
        pedidoDTO.setObservacion(pedido.getObservacion());
        return pedidoDTO;
    }
}
