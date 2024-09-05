package com.example.logistic.model.ruta;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.paquete.TipoPaquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.UbicacionDeposito;
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
    @JoinColumn(name="id_viaje")
    private List<Viaje> viajes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_driver")
    private Driver driver;
    private boolean completada;
    @Temporal(TemporalType.DATE)
    private Date date;

    public Ruta() {

    }
    public Ruta (Driver driver, List<Paquete> paquetes) {
        for (int i = 0; i < paquetes.size(); i++) {
            Viaje viaje = new Viaje(driver, paquetes.get(i));
            viaje.setDriver(driver);
            if (this.viajes.size() == 0) {
                viaje.setUbicacionOrigen(UbicacionDeposito.getInstance());
            } else {
                viaje.setUbicacionOrigen(paquetes.get(i-1).getUbicacionEntrega());
            }
            viaje.setOrden(this.viajes.size()-1);
            this.viajes.add(viaje);
        }
        this.driver = driver;
    }
    public void addViaje (Driver driver, Paquete paquete) {
        Viaje viaje = new Viaje(driver, paquete);
        viaje.setOrden(this.viajes.size());
        this.viajes.add(viaje);
    }

    public void removeViaje (Viaje viaje) {
        this.viajes.remove(viaje);
    }
    public void cambiarOrden(Viaje viaje1, Viaje viaje2){

    }

    public boolean isCompletada() {
        for (int i = 0; i < viajes.size(); i++) {
            if (!viajes.get(i).isCompletado()) {
                return false;
            }
        } return true;
    }
}
