package com.example.logistic.model.dtos;

import com.example.logistic.model.roles.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter

public class UsuarioDTOMini {
    private UUID uuid;
    private String name;
    private Role role;
    private boolean isActive;
}
