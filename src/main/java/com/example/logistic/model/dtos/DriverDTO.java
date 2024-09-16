package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO extends PersonaDTO{
    private Long vehiculoId;
    private Long rutaDiaria;

    // Constructores, getters y setters
}