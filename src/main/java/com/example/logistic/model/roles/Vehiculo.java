package com.example.logistic.model.roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehiculo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private TipoVehiculo tipoVehiculo;
    private String patente;

    public Vehiculo(TipoVehiculo tipoVehiculo, String patente) {
        this.tipoVehiculo = tipoVehiculo;
        this.patente = patente;
    }

    public Vehiculo() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
