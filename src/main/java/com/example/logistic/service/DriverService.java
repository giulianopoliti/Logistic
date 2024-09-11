package com.example.logistic.service;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.repository.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverMapper driverMapper;

    public Driver getDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver no encontrado"));
        return driver;
    }


    public void save (Driver driver) {
        if (driver == null) {
            throw new RuntimeException("Error al recibir el driver, es nulo");
        }
        driverRepository.save(driver);
    }

}