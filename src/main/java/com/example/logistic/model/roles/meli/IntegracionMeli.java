package com.example.logistic.model.roles.meli;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_integracion", discriminatorType = DiscriminatorType.STRING)
public abstract class IntegracionMeli {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accessToken;
    private String refreshToken;
    private Date expiresAt;

    public boolean isTokenExpired() {
        return new Date().after(this.expiresAt);
    }

    public void refreshToken(){};
}

