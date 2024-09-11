package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonaDTO {
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private Long tenantId;

    public PersonaDTO(Long id, String name, String lastName, String username, Long tenantId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.tenantId = tenantId;
    }
}
