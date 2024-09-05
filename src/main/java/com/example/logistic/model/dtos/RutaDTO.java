package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.Viaje;

import java.util.Date;
import java.util.List;
public class RutaDTO {
    private Integer id;
    private Date date;
    private Ubicacion ubicacionDeposito;
    private Integer driverId;
    private List<ViajeDTO> viajes;

    // Constructores, getters y setters
}
