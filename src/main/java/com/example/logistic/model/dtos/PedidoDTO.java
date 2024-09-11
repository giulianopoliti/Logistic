package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PedidoDTO {
    private Integer id;
    private String contenido;
    private Integer clienteId;
    private Date date;
    private EstadoPedido estadoPedido;
    private Ubicacion ubicacionActual;
    private Ubicacion ubicacionEntrega;
    private Integer localId;
    private TipoPedido tipoPedido;
}