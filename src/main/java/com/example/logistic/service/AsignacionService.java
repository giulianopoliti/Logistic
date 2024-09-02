package com.example.logistic.service;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.model.dtos.AsignacionDTO;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Viaje;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {
    private final PaqueteService paqueteService;
    private final DriverService driverService;
    private final RutaService rutaService;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    public AsignacionService(PaqueteService paqueteService,
                             DriverService driverService,
                             RutaService rutaService) {
        this.paqueteService = paqueteService;
        this.driverService = driverService;
        this.rutaService = rutaService;
    }

    @Transactional
    public void asignarPaqueteADriver(Integer paqueteId, Integer driverId) {
        DriverDTO driverDTO = driverService.getDriverById(driverId);
        PaqueteDTO paqueteDTO = paqueteService.getPaqueteById(paqueteId);
        Driver driver = driverMapper.toEntity(driverDTO);
        Paquete paquete = paqueteMapper.toEntity(paqueteDTO);
        driver.asignarPaquete(paquete);
    }
}
