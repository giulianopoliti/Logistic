package com.example.logistic.model.paquete;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
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
    private Ubicacion ubicacionEntrega;
    @Embedded
    private Ubicacion ubicacionActual;

    public Paquete() {

    }
    public Paquete(String contenido, Cliente cliente, TipoPaquete tipoPaquete, Ubicacion ubicacionEntrega) {
        this.contenido = contenido;
        this.cliente = cliente;
        this.ubicacionActual = this.cliente.getLocales().getFirst().getUbicacion(); // modificar
        this.tipoPaquete = tipoPaquete;
        this.ubicacionEntrega = ubicacionEntrega;
    }

    public void llegarADeposito() {
        this.estadoPaquete = EstadoPaquete.EnDepositoSinDriver;
    }
    public void encaminarPaquete () {
        this.estadoPaquete = EstadoPaquete.EnCamino;
    }
    public void entregarPaquete() {
        this.estadoPaquete = EstadoPaquete.Entregado;
        this.ubicacionActual = ubicacionEntrega;
        // logica para guardar paquete en db
    }

}
