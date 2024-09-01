package com.example.logistic.model.ruta;

import jakarta.persistence.*;

@Embeddable
public class Ubicacion {
    private double latitud;
    private double longitud;

    public Ubicacion(double latitud, double longitud) {
        if (esCoordenadaValida(latitud, longitud)) {
            this.latitud = latitud;
            this.longitud = longitud;
        } else {
            throw new IllegalArgumentException("Coordenadas no válidas");
        }
    }

    public Ubicacion() {

    }

    private boolean esCoordenadaValida(double latitud, double longitud) {
        return latitud >= -90.0 && latitud <= 90.0 && longitud >= -180.0 && longitud <= 180.0;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }

}
