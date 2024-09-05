package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO extends PersonaDTO{
    private Integer vehiculoId;

    public DriverDTO(Integer id, String nombre, String apellido, String username, Integer tenantId, Integer vehiculoId) {
        super(id, nombre, apellido, username, tenantId);
        this.vehiculoId = vehiculoId;
    }

    // Constructores, getters y setters
}