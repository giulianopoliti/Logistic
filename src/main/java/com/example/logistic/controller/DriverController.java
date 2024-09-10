package com.example.logistic.controller;


import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.*;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.service.DriverService;
import com.example.logistic.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

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
    public ResponseEntity<DriverDTO> crearDriver (@RequestBody Map<String, Object> driverData) {
        Tenant tenant = tenantService.getById((Integer) driverData.get("tenantId"));
        // aca deberiamos encriptar la contraseña
        Driver driver = new Driver((String)driverData.get("name"),
                (String)driverData.get("lastName"),
                (Date) driverData.get("dateOfBirth"),
                tenant,
                (String)driverData.get("email"),
                (String)driverData.get("username"),
                (String)driverData.get("password"),
                (Role)driverData.get("role"));
        return ResponseEntity.ok(driverMapper.toDTO(driver));
    }
    @PutMapping("/agregarVehiculo")
    public ResponseEntity<DriverDTO> agregarVehiculo(@RequestParam Integer driverId, @RequestBody Vehiculo vehiculo) {
        // Obtener el Driver por su ID
        Driver driver = driverService.getDriverById(driverId);
        if (driver == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        // Agregar el vehículo al Driver
        driver.modificarVehiculo(vehiculo);
        // Guardar los cambios
        driverService.save(driver);

        // Devolver la respuesta
        return ResponseEntity.ok(driverMapper.toDTO(driver));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Integer id) {
        Driver driver = driverService.getDriverById(id);
        DriverDTO driverDTO = driverMapper.toDTO(driver);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}/ruta-diaria")
    public ResponseEntity<Ruta> getRutaDiaria(@PathVariable DriverDTO driverDTO) {
        Driver driver = driverService.getDriverById(driverDTO.getId());
        return null;
    }

    @PutMapping("/{id}/paquetes/{paqueteId}/entregado")
    public ResponseEntity<Void> marcarPaqueteEntregado(@PathVariable Integer id, @PathVariable Integer paqueteId) {
        // Implementación
        return null;
    }
}
