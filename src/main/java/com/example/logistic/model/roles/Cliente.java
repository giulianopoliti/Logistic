package com.example.logistic.model.roles;

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
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    @JoinColumn(name = "cliente")
    private List<Local> locales = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "cliente")
    private List<Paquete> paquetes = new ArrayList<>();

    public Cliente(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password, Role role) {
        super(name, lastName, dateOfBirth, tenant, email, username, password, role);
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
