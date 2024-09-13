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

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private TenantService tenantService;

    public Driver getDriverById(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver no encontrado"));
        return driver;
    }
    public List<Driver> getDrivers() {
        return driverRepository.findAll();
    }

    public void save (Driver driver) {
        if (driver == null) {
            throw new RuntimeException("Error al recibir el driver, es nulo");
        }
        driverRepository.save(driver);
    }
    public Driver modificarVehiculo (Long driverId, Vehiculo vehiculo) {
        Driver driver = getDriverById(driverId);
        if (driver == null) {
            throw new EntityNotFoundException("Driver not found");
        }
        driver.setVehiculo(vehiculo);
        return driverRepository.save(driver);
    }
    public Driver crearDriver(Map<String, Object> driverData) {
        // Verificar si el tenant existe
        Tenant tenant = tenantService.getById((Integer) driverData.get("tenantId"));
        if (tenant == null) {
            throw new ResourceNotFoundException("Tenant not found");
        }
        Driver d = getDriverById((Long) driverData.get("id"));
        if (d != null) {
            throw new EntityExistsException("Driver already exists");
        }
        // Verificar si los datos son válidos
        if (driverData.get("name") == null || driverData.get("email") == null) {
            throw new RuntimeException("Name and email are required");
        }

        // Crear el driver
        Driver driver = new Driver(
                (String) driverData.get("name"),
                (String) driverData.get("lastName"),
                (Date) driverData.get("dateOfBirth"),
                tenant,
                (String) driverData.get("email"),
                (String) driverData.get("username"),
                (String) driverData.get("password")
        );

        // Guardar el driver
        return driverRepository.save(driver);
    }

}