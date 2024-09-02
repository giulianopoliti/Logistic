package com.example.logistic.controller;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer id) {
        // Implementación
        return null;
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        // Implementación
        return null;

    }

    @GetMapping("/{id}/paquetes")
    public ResponseEntity<List<Paquete>> getPaquetesCliente(@PathVariable Integer id) {
        // Implementación
        return null;

    }

}
