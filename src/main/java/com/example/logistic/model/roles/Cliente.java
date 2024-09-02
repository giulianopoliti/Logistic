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
    public Cliente(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, List<Local> locales) {
        super(name, lastName, dateOfBirth, tenant, email);
        this.locales = locales;
    }

    public Cliente() {

    }
    public void addLocal(Local local) {
        this.locales.add(local);
    }
    public void removeLocal(Local local) {
        this.locales.remove(local);
    }

    public void cargarPaqueteParticular(String contenido, Ubicacion ubicacion){
        Paquete paquete = new Paquete(contenido, this, TipoPaquete.Particular, ubicacion);
    }
    public void cargarPaqueteMercadoLibre(String contenido, Ubicacion ubicacion) {
        Paquete paquete = new Paquete(contenido, this, TipoPaquete.MercadoLibre, ubicacion);
        this.paquetes.add(paquete);
    }
    public void removePaquete(Paquete paquete) {
        this.paquetes.remove(paquete);
    }

    public void cargarPaquetesExcel() {
        /// implementar logica
    }
}
