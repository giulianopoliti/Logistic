package com.example.logistic.controller;


import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;

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
