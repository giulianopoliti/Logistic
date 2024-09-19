package com.example.logistic.controller;


import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.*;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.service.DriverService;
import com.example.logistic.service.TenantService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private TenantService tenantService;
    @PostMapping

    public ResponseEntity<DriverDTO> crearDriver(@RequestBody Map<String, Object> driverData) throws InstantiationException, IllegalAccessException {
        Driver driver = driverService.createDriver(driverData);
        DriverDTO driverDTO = driverMapper.toDTO(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(driverDTO);
    }
    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/agregarVehiculo")
    public ResponseEntity<DriverDTO> agregarVehiculo(@RequestParam UUID driverId, @RequestBody Vehiculo vehiculo) {
        Driver driver = driverService.modificarVehiculo(driverId, vehiculo);
        return ResponseEntity.ok(driverMapper.toDTO(driver));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable UUID id) {
        Driver driver = driverService.getDriverById(id);
        DriverDTO driverDTO = driverMapper.toDTO(driver);
        return ResponseEntity.ok(driver);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DriverDTO>> getDrivers () {
        List <Driver> drivers = driverService.getDrivers();
        List<DriverDTO> driverDTOS = drivers.stream().map(driver -> driverMapper.toDTO(driver)).toList();
        return ResponseEntity.ok(driverDTOS);
    }
    @PreAuthorize("hasRole('DRIVER')")
    @GetMapping("/{id}/ruta-diaria")
    public ResponseEntity<Ruta> getRutaDiaria(@PathVariable DriverDTO driverDTO) {
        Driver driver = driverService.getDriverById(driverDTO.getUuid());
        return null;
    }
}
