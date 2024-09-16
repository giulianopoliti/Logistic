package com.example.logistic.model.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TenantDTO {
    private Long id;

    @NotBlank(message = "El nombre del tenant no puede estar vacío")
    @Size(min = 2, max = 100, message = "El nombre del tenant debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El dominio no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "El dominio debe ser válido")
    private String domain;

    @NotNull(message = "El estado activo/inactivo no puede ser nulo")
    private Boolean activo;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @Pattern(regexp = "^[A-Z]{2}$", message = "El código de país debe ser de 2 letras mayúsculas")
    private String codigoPais;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "El número de teléfono debe ser válido")
    private String telefonoContacto;

    @Email(message = "El email debe ser válido")
    private String emailContacto;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaUltimaActualizacion;

    @Min(value = 0, message = "El límite de usuarios no puede ser negativo")
    private Integer limiteUsuarios;
}
