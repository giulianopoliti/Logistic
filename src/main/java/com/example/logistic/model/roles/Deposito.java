package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.Ubicacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

// Reveer esto, para vender el software, vamos a tener que tener muchos depositos, es decir una tabla deposito
@Entity
@Getter
@Setter
public class Deposito {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;
    @Embedded
    private Ubicacion ubicacion;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
