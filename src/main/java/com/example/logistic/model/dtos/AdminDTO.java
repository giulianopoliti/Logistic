package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO extends PersonaDTO{
    public AdminDTO(Integer id, String name, String lastName, String username, Integer tenantId) {
        super(id, name, lastName, username, tenantId);
    }
}
