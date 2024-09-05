package com.example.logistic.controller;

import com.example.logistic.mapper.ClienteMapper;
import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;


    @GetMapping("/{id}")
    public ResponseEntity<List<ClienteDTO>> getClientesByTenant(@PathVariable Tenant tenant) {
        List<Cliente> clientes = clienteService.findClientesByTenant(tenant);
        List<ClienteDTO> clienteDTOS = clientes.stream().map(clienteMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(clienteDTOS);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        return null;
    }

    @GetMapping("/{id}/paquetes")
    public ResponseEntity<List<PaqueteDTO>> getPaquetesCliente(@PathVariable ClienteDTO clienteDTO) {
        return null;
    }

}
