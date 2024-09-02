package com.example.logistic.model.ruta;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "paquete_id")
    private Paquete paquete;

    private Integer orden;

    private boolean completado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @JoinColumn(name="id_driver")
    @ManyToOne
    private Driver driver;
    @Embedded
    private Ubicacion ubicacionOrigen;
    @Embedded
    private Ubicacion ubicacionDestino;

    public Viaje(){

    }
    public Viaje (Driver driver, Paquete paquete) {
        this.driver = driver;
        this.paquete = paquete;
        this.ubicacionDestino = paquete.getUbicacionEntrega();
        this.completado = false;
    }

    // Getters y setters
}
