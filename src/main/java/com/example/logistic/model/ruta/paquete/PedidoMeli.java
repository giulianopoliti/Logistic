package com.example.logistic.model.ruta.paquete;

import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ubicacion;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoMeli extends Pedido{
    private String orderId; // ID pedido mercado libre
    private String sellerId; // ID vendedor MELI

    public PedidoMeli(String contenido, Vendedor vendedor, Ubicacion ubicacionEntrega, Ubicacion ubicacionActual, Tenant tenant, String orderId, String sellerId, Driver driver) {
        super(contenido, vendedor, ubicacionEntrega, ubicacionActual, tenant, driver);
        this.orderId = orderId;
        this.sellerId = sellerId;
    }

    public PedidoMeli() {
        super();
    }
}
