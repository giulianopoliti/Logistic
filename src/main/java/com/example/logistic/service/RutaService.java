package com.example.logistic.service;

import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.paquete.Pedido;
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

    public Ruta actualizarViajes(Integer rutaId, List<Pedido> pedidos) {
        Ruta ruta = getRutaById(rutaId);
        ruta.setPedidos(pedidos);
        return rutaRepository.save(ruta);
    }
    public Ruta findByDriverId(Integer driverId) {
        return rutaRepository.findByDriverId(driverId, new Date()); // esto te trae la primera ruta que encuentra del driver
    }
    public List<Ruta> findByDay (Date date, Integer tenantId) {
        return rutaRepository.findByDay(date, tenantId);
    }

}
