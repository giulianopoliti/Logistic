package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.paquete.TipoPaquete;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Cliente extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public Cliente(String name, String lastName, Date dateOfBirth, Tenant tenant, String email) {
        super(name, lastName, dateOfBirth, tenant, email);
    }

    public Cliente() {

    }

    public void cargarPaqueteParticular(String contenido, Ubicacion ubicacion){
        Paquete paquete = new Paquete(contenido, this, TipoPaquete.Particular, ubicacion);
    }
    public void cargarPaqueteMercadoLibre(String contenido, Ubicacion ubicacion) {
        Paquete paquete = new Paquete(contenido, this, TipoPaquete.MercadoLibre, ubicacion);
    }
}
