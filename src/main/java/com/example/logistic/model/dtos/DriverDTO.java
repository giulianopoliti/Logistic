package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Tenant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String username;
    private Integer vehiculoId;
    // Constructores, getters y setters
}