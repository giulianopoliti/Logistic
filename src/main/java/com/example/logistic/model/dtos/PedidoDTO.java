package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class PedidoDTO {
    private Long id;
    private String contenido;
    private Long vendedorId;
    private Date fechaCreacion;
    private EstadoPedido estadoPedido;
    private Ubicacion ubicacionEntrega;
    private Ubicacion ubicacionActual;
    private Long tenantId;
    private Long rutaId;
    private Long driverId;
    private String compradorName;
    private String observacion;
    private TipoPedido tipoPedido;
}