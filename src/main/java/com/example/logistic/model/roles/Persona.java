package com.example.logistic.model.roles;

import jakarta.persistence.*;

import java.util.Date;
// Persona.java
@MappedSuperclass
public abstract class Persona {
    private String name;
    private String lastName;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    private String email;
    private String username;
    private String password; // Será hasheada antes de almacenarse
    @Enumerated(EnumType.STRING)
    private Role role;

    public Persona(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password, Role role) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tenant = tenant;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Persona() {

    }
    // Getters y setters
}
