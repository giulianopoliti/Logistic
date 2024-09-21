package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Setter
@Getter

public class UsuarioDTOMini {
    private UUID uuid;
    private String name;
    private String role;
    private boolean isActive;
}
