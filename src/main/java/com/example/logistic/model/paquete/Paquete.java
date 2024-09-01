package com.example.logistic.model.paquete;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Paquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private EstadoPaquete estadoPaquete;
    @Enumerated(EnumType.STRING)
    private TipoPaquete tipoPaquete;

    @Embedded
    private Ubicacion ubicacion;

    public Paquete(String contenido, Cliente cliente, TipoPaquete tipoPaquete, Ubicacion ubicacion) {
        this.contenido = contenido;
        this.cliente = cliente;
        this.date = new Date();
        this.estadoPaquete = EstadoPaquete.EnLoDelCliente;
        this.tipoPaquete = tipoPaquete;
        this.ubicacion = ubicacion;
    }

    public Paquete() {

    }

    public void llegarADeposito() {
        this.estadoPaquete = EstadoPaquete.EnDepositoSinDriver;
    }
    public void encaminarPaquete () {
        this.estadoPaquete = EstadoPaquete.EnCamino;
    }
    public void entregarPaquete() {
        this.estadoPaquete = EstadoPaquete.Entregado;
        // logica para guardar paquete en db
    }

}
