package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class VendedorDTO extends PersonaDTO{
    public VendedorDTO(Long id, String nombre, String apellido, String username, Long tenantId) {
        super(id, nombre, apellido, username, tenantId);
    }


    // Constructores, getters y setters
}
