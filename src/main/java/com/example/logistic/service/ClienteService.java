package com.example.logistic.service;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
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

    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Paquete> getPaquetesCliente(Integer clienteId) {
        Cliente cliente = getClienteById(clienteId);
        return cliente.getPaquetes();
    }

    public Paquete createPaquete(Integer clienteId, Paquete paquete) {
        Cliente cliente = getClienteById(clienteId);
        paquete.setCliente(cliente);
        return paqueteRepository.save(paquete);
    }
}