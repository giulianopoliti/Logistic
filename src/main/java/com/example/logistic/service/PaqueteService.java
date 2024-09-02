package com.example.logistic.service;

import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaqueteService {
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private PaqueteMapper paqueteMapper;

    public PaqueteDTO getPaqueteById(Integer id) {
        Paquete paquete = paqueteRepository.findById(id).orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
        return paqueteMapper.toDTO(paquete);
    }

    public Paquete actualizarEstadoPaquete(Integer id, EstadoPaquete estado) {
        PaqueteDTO paquetedto = getPaqueteById(id);
        Paquete paquete = paqueteMapper.toEntity(paquetedto);
        paquete.setEstadoPaquete(estado);
        return paqueteRepository.save(paquete);
    }
}

