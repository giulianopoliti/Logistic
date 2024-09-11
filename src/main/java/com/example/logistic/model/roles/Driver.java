package com.example.logistic.model.roles;

import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.paquete.Pedido;
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
    private List<Pedido> pedidos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "integracion_meli_id")
    private IntegracionMeliDriver integracionMeliDriver;
    @Enumerated(EnumType.STRING)
    private EstadoDriver estadoDriver;


    public Driver(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }

    public Driver() {

    }
    public void modificarVehiculo (Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    // ver que devolver aca
    public Ruta crearRuta (List<Pedido> pedidos) {
        Ruta ruta = new Ruta();
        ruta.setPedidos(pedidos);
        ruta.setDriver(this);
        this.rutas.add(ruta);
        return ruta;
    }

}
