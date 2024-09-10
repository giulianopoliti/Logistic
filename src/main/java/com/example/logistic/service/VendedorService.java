package com.example.logistic.service;

import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.repository.PedidoRepository;
import com.example.logistic.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public Vendedor findById(Integer id) {
        return vendedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
    public List<Vendedor> findClientesByTenant (Tenant tenant) {
        return vendedorRepository.findClientesByTenant(tenant);
    }

    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public List<Pedido> findPaquetesPorCliente(Integer clienteId) {
        Vendedor vendedor = findById(clienteId);
        return vendedor.getPedidos();
    }

    public Pedido createPaquete(Integer vendedorId, Pedido pedido) {
        Vendedor vendedor = findById(vendedorId);
        pedido.setVendedor(vendedor);
        return pedidoRepository.save(pedido);
    }
}