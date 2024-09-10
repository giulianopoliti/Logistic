package com.example.logistic.model.ruta.paquete;

import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.roles.Driver;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "paquetes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pedido", discriminatorType = DiscriminatorType.STRING)
public abstract class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String contenido;
    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Enumerated(EnumType.STRING)
    private EstadoPaquete estadoPaquete;
    @Embedded
    private Ubicacion ubicacionEntrega;
    @Embedded
    private Ubicacion ubicacionActual;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    private double precio;

    public Pedido() {
        this.date = new Date();
    }

    public Pedido(String contenido, Vendedor vendedor, Ubicacion ubicacionEntrega, Ubicacion ubicacionActual, Tenant tenant, Driver driver) {
        this();
        this.contenido = contenido;
        this.vendedor = vendedor;
        this.ubicacionEntrega = ubicacionEntrega;
        this.ubicacionActual = ubicacionActual;
        this.tenant = tenant;
        this.driver = driver;
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
