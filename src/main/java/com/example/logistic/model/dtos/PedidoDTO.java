package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.Ubicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public abstract class PedidoDTO {
    private Long id;
    private String contenido;
    private UUID vendedorId;
    private Date fechaCreacion;
    private EstadoPedido estadoPedido;
    private Ubicacion ubicacionEntrega;
    private Ubicacion ubicacionActual;
    private Long tenantId;
    private Long rutaId;
    private UUID driverId;
    private String compradorName;
    private String observacion;
    private TipoPedido tipoPedido;
    private String codigoSeguimiento;
}