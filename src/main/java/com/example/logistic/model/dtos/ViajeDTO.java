package com.example.logistic.model.dtos;

import com.example.logistic.model.ruta.TipoViaje;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ViajeDTO {
    private Integer id;
    private Integer rutaId;
    private Integer paqueteId;
    private Integer orden;
    private boolean completado;
    private Date date;
    private TipoViaje tipoViaje;
}

