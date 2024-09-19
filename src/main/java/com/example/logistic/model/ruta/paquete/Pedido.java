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
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pedido", discriminatorType = DiscriminatorType.STRING)
public abstract class Pedido {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;

    private String contenido;

    @ManyToOne
    @JoinColumn(name = "id_vendedor")
    private Vendedor vendedor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estadoPedido;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitud", column = @Column(name = "ubicacion_entrega_latitud")),
            @AttributeOverride(name = "longitud", column = @Column(name = "ubicacion_entrega_longitud"))
    })
    private Ubicacion ubicacionEntrega;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitud", column = @Column(name = "ubicacion_actual_latitud")),
            @AttributeOverride(name = "longitud", column = @Column(name = "ubicacion_actual_longitud"))
    })
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
    private String compradorName;
    private String observacion;

    public Pedido() {
        this.fechaCreacion = new Date();
    }

    public Pedido(String contenido, Vendedor vendedor, Ubicacion ubicacionEntrega, Ubicacion ubicacionActual, Tenant tenant,String compradorName) {
        this();
        this.contenido = contenido;
        this.vendedor = vendedor;
        this.ubicacionEntrega = ubicacionEntrega;
        this.ubicacionActual = ubicacionActual;
        this.tenant = tenant;
        this.compradorName = compradorName;
    }

    public void llegarADeposito() {
        this.estadoPedido = EstadoPedido.EN_DEPOSITO;
    }
    public void encaminarPaquete () {
        this.estadoPedido = EstadoPedido.EN_CAMINO;
    }
    public void marcarPaqueteEntregado() {
        this.estadoPedido = EstadoPedido.ENTREGADO;
        this.ubicacionActual = ubicacionEntrega;
    }
    public void marcarPaqueteFallido () {
        this.estadoPedido = EstadoPedido.FALLIDO;
    }

}
