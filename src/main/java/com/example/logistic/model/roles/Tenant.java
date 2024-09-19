package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String nombre;
    private String razonSocial;  // Agregado para nombre legal completo
    private boolean activo;
    private String domain;

    @Email(message = "Email debe ser válido")
    private String email;

    private String phone;
    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt = new Date();

    @Pattern(regexp = "\\d{2}-\\d{8}-\\d{1}", message = "CUIT debe seguir el formato ##-########-#")
    private String CUIT;

    private Integer limiteUsuarios;

    private String logoURL;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private List<Deposito> depositos;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;
}

