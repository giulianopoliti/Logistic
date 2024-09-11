package com.example.logistic.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantDTO {
    private Long id;
    private String nombre;
    private String domain;
}
