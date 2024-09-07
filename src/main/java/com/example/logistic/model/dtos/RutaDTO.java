package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.Viaje;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class RutaDTO {
    private Integer id;
    private Date date;
    private Ubicacion ubicacionDeposito;
    private Integer driverId;
    private List<ViajeDTO> viajes;

    // Constructores, getters y setters
}
