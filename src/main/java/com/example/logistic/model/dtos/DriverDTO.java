package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO extends PersonaDTO{
    private Long vehiculoId;

    public DriverDTO(Long id, String nombre, String apellido, String username, Long tenantId, Long vehiculoId) {
        super(id, nombre, apellido, username, tenantId);
        this.vehiculoId = vehiculoId;
    }

    // Constructores, getters y setters
}