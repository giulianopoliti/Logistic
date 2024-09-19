package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.paquete.EstadoPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PedidoMapaDTO {
    private UUID uuid;
    private Ubicacion ubicacionEntrega;
    private EstadoPedido estadoPedido;
}
