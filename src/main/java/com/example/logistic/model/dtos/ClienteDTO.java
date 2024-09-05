package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ClienteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String username;

    // Constructores, getters y setters
}
