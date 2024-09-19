package com.example.logistic.model.roles;

import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Entity
@Setter
@Getter
@DiscriminatorValue("DRIVER")
@Table(name = "usuarios")
public class Driver extends Usuario {
    @OneToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Ruta> rutas;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "integracion_meli_id")
    private IntegracionMeliDriver integracionMeliDriver;
    @Enumerated(EnumType.STRING)
    private EstadoDriver estadoDriver;


    public Driver(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone, String username, String cuil, String address, Date createdAt, String profilePictureURL, Vehiculo vehiculo, IntegracionMeliDriver integracionMeliDriver, EstadoDriver estadoDriver) {
        super(name, lastName, dateOfBirth, tenant, email, phone, emergencyPhone, username, cuil, address, createdAt, profilePictureURL);
        this.vehiculo = vehiculo;
        this.rutas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.integracionMeliDriver = integracionMeliDriver;
        this.estadoDriver = EstadoDriver.EN_DESCANSO;
    }

    public Driver() {
        super();
        this.setRol(Role.DRIVER);
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_DRIVER"));
    }

}
