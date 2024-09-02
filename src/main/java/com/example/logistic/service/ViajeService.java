package com.example.logistic.service;

import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.repository.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViajeService {
    @Autowired
    private ViajeRepository viajeRepository;
    public void save (Viaje viaje) {
        viajeRepository.save(viaje);
    }
    public Viaje findById(Integer id) {
        return viajeRepository.findById(id).orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
    }
}
