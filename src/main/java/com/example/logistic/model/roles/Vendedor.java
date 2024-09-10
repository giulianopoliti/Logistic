package com.example.logistic.model.roles;

import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
import com.example.logistic.model.roles.meli.IntegracionMeliVendedor;
import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Vendedor extends Usuario {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private List<Local> locales = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vendedor_id")
    private List<IntegracionMeliVendedor> integracionMeliVendedor;

    public Vendedor(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }

    public Vendedor() {
        super();
    }


    public void addLocal(Local local) {
        this.locales.add(local);
    }
    public void removeLocal(Local local) {
        this.locales.remove(local);
    }

    public void removePaquete(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    public void cargarPaquetesExcel() {
        /// implementar logica
    }
}
