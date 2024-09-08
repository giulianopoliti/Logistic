package com.example.logistic.model.roles;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
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
public class Driver extends Usuario {
    @OneToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "driver_id", fetch = FetchType.LAZY)
    private List<Ruta> rutas;

    @OneToMany(mappedBy = "driver_id", fetch = FetchType.LAZY)
    private List<Viaje> viajes;

    @Embedded
    private IntegracionMeliDriver integracionMeliDriver;


    public Driver(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }

    public Driver() {

    }
    public void modificarVehiculo (Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // ver que devolver aca
    public Ruta crearRuta (List<Paquete> paquetes) {
        Ruta ruta = new Ruta();
        for (int i = 0; i < paquetes.size(); i++) {
            ruta.addViaje(this, paquetes.get(i));
        }
        return ruta;
    }

}
