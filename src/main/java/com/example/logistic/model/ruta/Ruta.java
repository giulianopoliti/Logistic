package com.example.logistic.model.ruta;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.paquete.TipoPaquete;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_viaje")
    private List<Viaje> viajes = new ArrayList<>();
    @Embedded
    private Ubicacion ubicacionDeposito;

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;

    public Ruta(Integer id, List<Viaje> viajes, Ubicacion ubicacionDeposito, Driver driver) {
        this.id = id;
        this.viajes = viajes;
        this.ubicacionDeposito = ubicacionDeposito;
        this.driver = driver;
    }

    public Ruta() {

    }
}
