package com.example.logistic.model.roles.meli;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_integracion", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
public abstract class IntegracionMeli {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String accessToken;
    private String refreshToken;
    private Date expiresAt;

}

