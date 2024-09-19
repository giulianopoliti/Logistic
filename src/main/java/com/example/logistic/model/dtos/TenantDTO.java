package com.example.logistic.model.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TenantDTO {
    private UUID uuid;

    @NotBlank(message = "El nombre del tenant no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre del tenant debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El dominio no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El dominio debe ser válido")
    private String domain;

    @NotNull(message = "El estado activo/inactivo no puede ser nulo")
    private Boolean activo;

    @Size(max = 100, message = "La razon social no puede exceder los 500 caracteres")
    private String razonSocial;

    @Size(max = 100, message = "La direccion no puede exceder los 500 caracteres")
    private String address;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "El número de teléfono debe ser válido")
    private String phone;

    @Email(message = "El email debe ser válido")
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime fechaUltimaActualizacion;

    @Min(value = 0, message = "El límite de usuarios no puede ser negativo")
    private Integer limiteUsuarios;
    private String CUIT;
}
