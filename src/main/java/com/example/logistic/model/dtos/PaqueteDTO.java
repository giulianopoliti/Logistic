package com.example.logistic.model.dtos;

import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.TipoPaquete;
import com.example.logistic.model.ruta.Ubicacion;

import java.util.Date;

public class PaqueteDTO {
    private Integer id;
    private String contenido;
    private Integer clienteId;
    private Date date;
    private EstadoPaquete estadoPaquete;
    private TipoPaquete tipoPaquete;
    private Ubicacion ubicacionActual;
    private Ubicacion ubicacionEntrega;

    // Constructores, getters y setters
}