package com.example.logistic.model.roles;

import com.example.logistic.model.roles.meli.IntegracionMeliCliente;
import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.paquete.TipoPaquete;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Cliente extends Usuario {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private List<Local> locales = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private List<Paquete> paquetes = new ArrayList<>();

    @Embedded
    private IntegracionMeliCliente integracionMeliCliente;

    public Cliente(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }

    public Cliente() {
        super();
    }


    public void addLocal(Local local) {
        this.locales.add(local);
    }
    public void removeLocal(Local local) {
        this.locales.remove(local);
    }

    public void removePaquete(Paquete paquete) {
        this.paquetes.remove(paquete);
    }

    public void cargarPaquetesExcel() {
        /// implementar logica
    }
}
