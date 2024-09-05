package com.example.logistic.model.roles;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Viaje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
public class Driver extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;
    @ManyToOne
    @JoinColumn(name = "ruta_diaria")
    private Ruta rutaDiaria;

    public Driver(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password, Role role, Integer id, Vehiculo vehiculo, Ruta rutaDiaria) {
        super(name, lastName, dateOfBirth, tenant, email, username, password, role);
        this.id = id;
        this.vehiculo = vehiculo;
        this.rutaDiaria = rutaDiaria;
    }

    public Driver(
                  String name,
                  String lastName,
                  Date dateOfBirth,
                  Tenant tenant,
                  String email,
                  Vehiculo vehiculo) {

        this.vehiculo = vehiculo;
    }

    public Driver() {

    }
    public void crearRuta (List<Viaje> viajes) {
        Ruta ruta = new Ruta();
        ruta.setDriver(this);
        ruta.setViajes(viajes);
        this.setRutaDiaria(ruta);
    }
    public void asignarPaquete(Paquete paquete) {
        if (this.rutaDiaria == null) {
            rutaDiaria = new Ruta();
        }
        rutaDiaria.addViaje(this, paquete);
    }
}
