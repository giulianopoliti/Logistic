package com.example.logistic.controller;


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

    @GetMapping("/{id}")
    public ResponseEntity<DriverDTO> getDriver(@PathVariable Integer id) {
        DriverDTO driverDTO = driverService.getDriverById(id);
        return ResponseEntity.ok(driverDTO);
    }

    @GetMapping("/{id}/ruta-diaria")
    public ResponseEntity<Ruta> getRutaDiaria(@PathVariable Integer id) {
        // Implementación
        return null;

    }

    @PutMapping("/{id}/paquetes/{paqueteId}/entregado")
    public ResponseEntity<Void> marcarPaqueteEntregado(@PathVariable Integer id, @PathVariable Integer paqueteId) {
        // Implementación
        return null;
    }
}
