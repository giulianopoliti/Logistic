package com.example.logistic.service;

import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.model.ruta.paquete.EstadoPaquete;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;

    public Pedido getPedidoById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
    }

    public Pedido actualizarEstadoPedido(Integer id, EstadoPaquete estado) {
        Pedido pedido = getPedidoById(id);
        pedido.setEstadoPaquete(estado);
        return pedidoRepository.save(pedido);
    }
    public Pedido save (Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    /*public Page<PedidoRepository> findPaquetesByClienteId (Integer clienteId, Pageable pageable){
        return pedidoRepository.findPaquetesByClienteId(clienteId, pageable);
    }*/


    public List<Pedido> findPedidosByDriverId(Integer driverId) {
        List<Pedido> paquetes = new ArrayList<>();

        return paquetes;
    }
}

