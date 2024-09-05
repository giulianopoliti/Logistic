package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonaDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String username;
    private Integer tenantId;

    public PersonaDTO(Integer id, String name, String lastName, String username, Integer tenantId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.tenantId = tenantId;
    }
}
