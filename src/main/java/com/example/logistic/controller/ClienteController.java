package com.example.logistic.controller;

import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.*;
import com.example.logistic.service.PedidoService;
import com.example.logistic.service.TenantService;
import com.example.logistic.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private VendedorMapper vendedorMapper;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/registro")
    public ResponseEntity<VendedorDTO> crearClienteConAcceso (@RequestBody Map<String, Object> clienteData) {
        Tenant tenant = tenantService.getById((Integer) clienteData.get("tenantId"));
        Vendedor vendedor = new Vendedor((String)clienteData.get("name"),
                (String)clienteData.get("lastName"),
                (Date)clienteData.get("dateOfBirth"),
                tenant,
                (String)clienteData.get("email"),
                (String)clienteData.get("username"),
                (String)clienteData.get("password")
                );
        vendedorService.save(vendedor);
        return ResponseEntity.ok(vendedorMapper.toDTO(vendedor));
    }
    // Habria que crear para clientes sin acceso a la app. Y que pueda migrar los paquetes el administrador


    @GetMapping("/{id}")
    public ResponseEntity<List<VendedorDTO>> getVendedoresByTenant(@PathVariable Tenant tenant) {
        List<Vendedor> vendedores = vendedorService.findClientesByTenant(tenant);
        List<VendedorDTO> vendedorDTOS = vendedores.stream().map(vendedorMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(vendedorDTOS);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> createCliente(@RequestBody Vendedor vendedor) {
        return null;
    }


}
