package com.example.logistic.model.ruta;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;

import java.util.Date;

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
    // Getters y setters
}
