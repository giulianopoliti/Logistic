package com.example.logistic.service;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.repository.ClienteRepository;
import com.example.logistic.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PaqueteRepository paqueteRepository;

    public Cliente findById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
    public List<Cliente> findClientesByTenant (Tenant tenant) {
        return clienteRepository.findClientesByTenant(tenant);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Paquete> findPaquetesPorCliente(Integer clienteId) {
        Cliente cliente = findById(clienteId);
        return cliente.getPaquetes();
    }

    public Paquete createPaquete(Integer clienteId, Paquete paquete) {
        Cliente cliente = findById(clienteId);
        paquete.setCliente(cliente);
        return paqueteRepository.save(paquete);
    }
}