package com.example.logistic.controller;

import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {
    @Autowired
    private PaqueteService paqueteService;

    @GetMapping("/{id}")
    public ResponseEntity<Paquete> getPaquete(@PathVariable Integer id) {
        // Implementación
        return null;
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Paquete> actualizarEstadoPaquete(@PathVariable Integer id, @RequestBody EstadoPaquete estado) {
        PaqueteDTO paqueteDTO = paqueteService.getPaqueteById(id);
    }
}