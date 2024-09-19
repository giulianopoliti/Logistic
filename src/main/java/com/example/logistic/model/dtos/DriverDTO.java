package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DriverDTO extends UsuarioDTO{
    private UUID vehiculoUuid;
    private UUID rutaDiaria;
    // Constructores, getters y setters
}