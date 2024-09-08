package com.example.logistic.model.roles;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    private String email;
    private String username;
    private String password; // Será hasheada antes de almacenarse
    private boolean active;

    public Usuario(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tenant = tenant;
        this.email = email;
        this.username = username;
        this.password = password;
        this.active = true;
    }

    public Usuario() {

    }
    // Getters y setters
}
