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
    private UUID uuid;
    private String contenido;
    private UUID vendedorUuid;
    private Date fechaCreacion;
    private EstadoPedido estadoPedido;
    private Ubicacion ubicacionEntrega;
    private Ubicacion ubicacionActual;
    private UUID tenantUuid;
    private UUID rutaUuid;
    private UUID driverUuid;
    private String compradorName;
    private String observacion;
    private TipoPedido tipoPedido;
    private String codigoSeguimiento;
}