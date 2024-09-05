package com.example.logistic.service;

import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaqueteService {
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private ViajeService viajeService;

    public Paquete getPaqueteById(Integer id) {
        return paqueteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
    }

    public Paquete actualizarEstadoPaquete(Integer id, EstadoPaquete estado) {
        Paquete paquete = getPaqueteById(id);
        paquete.setEstadoPaquete(estado);
        return paqueteRepository.save(paquete);
    }
    public Paquete save (Paquete paquete) {
        return paqueteRepository.save(paquete);
    }
    public List<Paquete> findPaquetesByClienteId (Integer clienteId){
        return paqueteRepository.findPaquetesByClienteId(clienteId);
    }


    public List<Paquete> findPaquetesByDriverId(Integer driverId) {
        List<Viaje> viajes = viajeService.findViajesByDriverId(driverId);
        List<Paquete> paquetes = new ArrayList<>();

        for (Viaje viaje : viajes) {
            paquetes.add(viaje.getPaquete());
        }

        return paquetes;
    }
}

