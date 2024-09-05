package com.example.logistic.service;

import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;

    public Ruta getRutaById(Integer id) {
        return rutaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
    }

    public Ruta save(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public Ruta actualizarViajes(Integer rutaId, List<Viaje> viajes) {
        Ruta ruta = getRutaById(rutaId);
        ruta.setViajes(viajes);
        return rutaRepository.save(ruta);
    }
    public Ruta findByDriverId(Integer driverId) {
        return rutaRepository.findByDriverId(driverId, new Date()); // esto te trae la primera ruta que encuentra del driver
    }

}
