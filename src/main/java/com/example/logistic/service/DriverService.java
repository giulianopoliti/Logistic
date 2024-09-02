package com.example.logistic.service;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.repository.DriverRepository;
import com.example.logistic.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;
    @Autowired
    private DriverMapper driverMapper;

    public DriverDTO getDriverById(Integer id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new RuntimeException("Driver no encontrado"));
        driverMapper.toDTO(driver);
    }

    public Ruta getRutaDiaria(Integer driverId) {
        Driver driver = getDriverById(driverId);
        return driver.getRutaDiaria();
    }

    public void marcarPaqueteEntregado(Integer driverId, Integer paqueteId) {
        Paquete paquete = paqueteRepository.findById(paqueteId)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
        paquete.setEstadoPaquete(EstadoPaquete.Entregado);
        paqueteRepository.save(paquete);
    }
}