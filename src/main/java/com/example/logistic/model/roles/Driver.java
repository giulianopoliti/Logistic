package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.Ruta;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class Driver extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
    @OneToMany(mappedBy = "driver")
    private List<Ruta> rutas = new ArrayList<>();

    public Driver(
                  String name,
                  String lastName,
                  Date dateOfBirth,
                  Tenant tenant,
                  String email,
                  Vehiculo vehiculo) {
        super(name, lastName, dateOfBirth, tenant, email);
        this.vehiculo = vehiculo;
    }

    public Driver() {

    }
}
