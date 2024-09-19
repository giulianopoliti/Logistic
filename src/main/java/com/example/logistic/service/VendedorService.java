package com.example.logistic.service;

import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.TenantDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.repository.PedidoRepository;
import com.example.logistic.repository.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VendedorService extends UsuarioService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private VendedorMapper vendedorMapper;

    public Vendedor findById(UUID uuid) {
        return vendedorRepository.findById(uuid).orElseThrow(EntityNotFoundException::new);
    }
    public VendedorDTO findByIdAsDTO(UUID uuid) {
        return vendedorMapper.toDTO(findById(uuid));
    }
    public List<Vendedor> findVendedoresByTenant (TenantDTO tenantDTO) {
        List<Vendedor> vendedor = vendedorRepository.findVendedoresByTenant(tenantDTO.getUuid());
        return vendedor;
    }

    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public List<Pedido> findPaquetesPorCliente(UUID vendedorId) {
        Vendedor vendedor = findById(vendedorId);
        return vendedor.getPedidos();
    }

    public Pedido createPaquete(UUID vendedorId, Pedido pedido) {
        Vendedor vendedor = findById(vendedorId);
        pedido.setVendedor(vendedor);
        return pedidoRepository.save(pedido);
    }
    public VendedorDTO createVendedor(Map<String, Object> vendedorData) throws InstantiationException, IllegalAccessException {
        Vendedor vendedor = new Vendedor();
        // implementar logica para guardar la data
        return vendedorMapper.toDTO(save(vendedor));
    }
    public List<VendedorDTO> getAllVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return vendedores.stream().map(vendedorMapper::toDTO).collect(Collectors.toList());
    }
}