package com.example.logistic.service;

import com.example.logistic.errors.ResourceNotFoundException;
import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vehiculo;
import com.example.logistic.repository.DriverRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DriverService extends UsuarioService{
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private TenantService tenantService;

    public Driver getDriverById(UUID id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver no encontrado"));
        return driver;
    }
    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public Driver save (Driver driver) {
        if (driver == null) {
            throw new RuntimeException("Error al recibir el driver, es nulo");
        }
        return driverRepository.save(driver);
    }
    public Driver modificarVehiculo (UUID driverId, Vehiculo vehiculo) {
        Driver driver = getDriverById(driverId);
        if (driver == null) {
            throw new EntityNotFoundException("Driver not found");
        }
        driver.setVehiculo(vehiculo);
        return driverRepository.save(driver);
    }
    public Driver createDriver(Map<String, Object> driverData) throws InstantiationException, IllegalAccessException {
        Driver driver = new Driver();
        return save(driver);
    }

}