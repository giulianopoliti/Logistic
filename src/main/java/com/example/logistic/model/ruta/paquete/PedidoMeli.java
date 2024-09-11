package com.example.logistic.model.ruta.paquete;

import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ubicacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PedidoMeli extends Pedido{
    @Column(unique = true)
    private String orderId; // ID pedido mercado libre
    private String sellerId; // ID vendedor MELI

    public PedidoMeli(String contenido, Vendedor vendedor, Ubicacion ubicacionEntrega, Ubicacion ubicacionActual, Tenant tenant, String orderId, String sellerId, Driver driver, String compradorName) {
        super(contenido, vendedor, ubicacionEntrega, ubicacionActual, tenant, driver, compradorName);
        this.orderId = orderId;
        this.sellerId = sellerId;
    }

    public PedidoMeli() {
        super();
    }
}
