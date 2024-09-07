package com.example.logistic.model.paquete;

import com.example.logistic.model.roles.Local;
import com.example.logistic.model.roles.Tenant;
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
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public Paquete() {

    }
    public Paquete(String contenido, Cliente cliente, TipoPaquete tipoPaquete, Ubicacion ubicacionEntrega, Local local) {
        this.contenido = contenido;
        this.cliente = cliente;
        this.estadoPaquete = EstadoPaquete.EnLoDelCliente;
        this.ubicacionActual = local.getUbicacion();
        this.tipoPaquete = tipoPaquete;
        this.ubicacionEntrega = ubicacionEntrega;
    }

    public void llegarADeposito() {
        this.estadoPaquete = EstadoPaquete.EnDepositoSinDriver;
    }
    public void encaminarPaquete () {
        this.estadoPaquete = EstadoPaquete.EnCamino;
    }
    public void marcarPaqueteEntregado() {
        this.estadoPaquete = EstadoPaquete.Entregado;
        this.ubicacionActual = ubicacionEntrega;
    }
    public void marcarPaqueteFallido () {
        this.estadoPaquete = EstadoPaquete.Fallido;
    }

}
