package com.example.logistic.model.roles;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "configurations")
public class Configuration {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;
    private boolean modoOscuro;

}
