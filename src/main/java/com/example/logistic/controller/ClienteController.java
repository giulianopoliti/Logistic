package com.example.logistic.controller;

import com.example.logistic.mapper.ClienteMapper;
import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.*;
import com.example.logistic.service.ClienteService;
import com.example.logistic.service.PaqueteService;
import com.example.logistic.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private PaqueteService paqueteService;


    @PostMapping("/registro")
    public ResponseEntity<ClienteDTO> crearClienteConAcceso (@RequestBody Map<String, Object> clienteData) {
        Tenant tenant = tenantService.getById((Integer) clienteData.get("tenantId"));
        Cliente cliente = new Cliente((String)clienteData.get("name"),
                (String)clienteData.get("lastName"),
                (Date)clienteData.get("dateOfBirth"),
                tenant,
                (String)clienteData.get("email"),
                (String)clienteData.get("username"),
                (String)clienteData.get("password")
                );
        clienteService.save(cliente);
        return ResponseEntity.ok(clienteMapper.toDTO(cliente));
    }
    // Habria que crear para clientes sin acceso a la app. Y que pueda migrar los paquetes el administrador


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


}
