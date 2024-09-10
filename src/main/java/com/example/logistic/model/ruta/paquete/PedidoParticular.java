package com.example.logistic.model.ruta.paquete;

import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ubicacion;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PedidoParticular extends Pedido{
    public PedidoParticular(String contenido, Vendedor vendedor, Ubicacion ubicacionEntrega, Ubicacion ubicacionActual, Tenant tenant, Driver driver) {
        super(contenido, vendedor, ubicacionEntrega, ubicacionActual, tenant, driver);
    }

    public PedidoParticular() {
        super();
    }
}
