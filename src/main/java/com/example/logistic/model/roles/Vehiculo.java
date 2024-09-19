package com.example.logistic.model.roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Vehiculo {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private TipoVehiculo tipoVehiculo;
    private String patente;

    public Vehiculo(TipoVehiculo tipoVehiculo, String patente) {
        this.tipoVehiculo = tipoVehiculo;
        this.patente = patente;
    }

    public Vehiculo() {

    }


}
