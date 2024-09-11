package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDTO extends PersonaDTO{
    public AdminDTO(Long id, String name, String lastName, String username, Long tenantId) {
        super(id, name, lastName, username, tenantId);
    }
}
