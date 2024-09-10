package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class VendedorDTO extends PersonaDTO{
    public VendedorDTO(Integer id, String nombre, String apellido, String username, Integer tenantId) {
        super(id, nombre, apellido, username, tenantId);
    }


    // Constructores, getters y setters
}
