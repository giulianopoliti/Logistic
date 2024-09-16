package com.example.logistic.controller;

import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.TenantDTO;
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
@RequestMapping("/api/vendedores")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private VendedorMapper vendedorMapper;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/registro")
    public ResponseEntity<VendedorDTO> crearVendedorConAcceso (@RequestBody Map<String, Object> clienteData) {
        // Llama a la capa de servicio para manejar la lógica de creación y conversión
        VendedorDTO vendedorDTO = vendedorService.crearVendedorConAcceso(clienteData);
        return ResponseEntity.ok(vendedorDTO);
    }
    // Habria que crear para clientes sin acceso a la app. Y que pueda migrar los paquetes el administrador


    @GetMapping("/{id}")
    public ResponseEntity<List<VendedorDTO>> getVendedoresByTenant(@PathVariable TenantDTO tenantDTO) {
        List<Vendedor> vendedor = vendedorService.findVendedoresByTenant(tenantDTO);
        List<VendedorDTO> vendedorDTOS = vendedor.stream().map(vendedorMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(vendedorDTOS);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> createVendedor(@RequestBody Vendedor vendedor) {
        return null;
    }


}
