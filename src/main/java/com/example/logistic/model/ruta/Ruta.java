package com.example.logistic.model.ruta;


import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="id_pedido")
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;
    private boolean completada;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    public Ruta() {

    }
    public Ruta (Driver driver, List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.driver = driver;
        this.date = new Date();
    }
    public void addPedido (Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void removePedido (Pedido pedido) {
        this.pedidos.remove(pedido);
    }


    public boolean isCompletada() {
        for (int i = 0; i < pedidos.size(); i++) {
            if (!(pedidos.get(i).getEstadoPedido() == EstadoPedido.ENTREGADO)) {
                return false;
            }
        } return true;
    }
}
