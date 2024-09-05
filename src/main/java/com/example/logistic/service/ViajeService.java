package com.example.logistic.service;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.repository.ViajeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public List<Viaje> findViajesByDriverId (Integer driverId) {
        return viajeRepository.findViajesByDriverId(driverId);
    }
    public List<Viaje> findViajesByDriverIdHoy (Integer driverId, Date date) {
        return viajeRepository.findViajesByDriverIdHoy(driverId, date);
    }
}
