package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.Ubicacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Local {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    @Embedded
    private Ubicacion ubicacion;
}
