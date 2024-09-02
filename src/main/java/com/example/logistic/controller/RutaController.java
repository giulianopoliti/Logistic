package com.example.logistic.controller;

import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    private RutaService rutaService;

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> getRuta(@PathVariable Integer id) {
        // Implementación
        return null;
    }

    @PostMapping
    public ResponseEntity<Ruta> crearRuta(@RequestParam List<PaqueteDTO> paquetesDTO,
                                          DriverDTO driverDTO) {
        Ruta ruta = new Ruta();

    }

    @PutMapping("/{id}/viajes")
    public ResponseEntity<Ruta> actualizarViajes(@PathVariable Integer id, @RequestBody List<Viaje> viajes) {
        // Implementación
        return null;

    }
}