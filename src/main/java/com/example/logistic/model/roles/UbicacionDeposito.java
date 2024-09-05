package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.Ubicacion;
import lombok.Getter;
// Reveer esto, para vender el software, vamos a tener que tener muchos depositos, es decir una tabla deposito

public class UbicacionDeposito extends Ubicacion {
    @Getter
    private static final UbicacionDeposito instance = new UbicacionDeposito();
    public UbicacionDeposito(){
        super(1.0,1.0); // ubicacion fija.
    }
}
