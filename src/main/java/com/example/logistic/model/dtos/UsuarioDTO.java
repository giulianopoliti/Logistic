package com.example.logistic.model.dtos;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter

public abstract class UsuarioDTO {
    private UUID uuid;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String lastName;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 4, max = 20, message = "El nombre de usuario debe tener entre 4 y 20 caracteres")
    private String username;

    @NotNull(message = "El ID del tenant no puede ser nulo")
    private UUID tenantUuid;

    private boolean isActive;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "El número de teléfono debe ser válido")
    private String phone;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate dateOfBirth;

    private LocalDateTime createdAt;

    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String address;


}
