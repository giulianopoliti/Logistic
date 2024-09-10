package com.example.logistic.controller;


import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.TipoPedido;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.paquete.EstadoPaquete;

import com.example.logistic.model.roles.Local;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.model.ruta.paquete.PedidoParticular;
import com.example.logistic.service.LocalService;
import com.example.logistic.service.PedidoService;
import com.example.logistic.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/paquetes")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private VendedorMapper vendedorMapper;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private LocalService localService;

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPaqueteDTO(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoMapper.toDTO(pedidoService.getPaqueteById(id)));
    }

    // on click en paquete, que te muestre todo
    public ResponseEntity<Pedido> getPaquete (@RequestParam PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.getPaqueteById(pedidoDTO.getId());
        return ResponseEntity.ok(pedido);
    }
    public ResponseEntity<PedidoDTO> cargarPaqueteParticular(String contenido,
                                                           VendedorDTO vendedorDTO,
                                                           Ubicacion ubicacionEntrega,
                                                           Local local) {
        Pedido pedido = new PedidoParticular();

        pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }
    public ResponseEntity<PedidoDTO> cargarPaqueteML (String contenido,
                                                       VendedorDTO vendedorDTO,
                                                       Ubicacion ubicacionEntrega,
                                                       Local local) {
        Pedido pedido = new PedidoParticular(contenido, clienteService.findById(clienteDTO.getId()), TipoPaquete.MercadoLibre, ubicacionEntrega, local);
        pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }

    // Driver marca cuando entrega un paquete
    public ResponseEntity<PedidoDTO> marcarPaqueteEntregado (@RequestParam PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.marcarPaqueteEntregado();
        pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }

    // El driver puede marcar cuando no llega a entregar un paquete

    public ResponseEntity<List<PedidoDTO>> getPaquetesCliente (@RequestBody VendedorDTO vendedorDTO,
                                                                @RequestParam int page,
                                                                @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Pedido> paquetes = pedidoService.findPedidosByDriverId(vendedorDTO.getId(), pageable);
        List<PedidoDTO> paqueteDTOS = paquetes.stream().map(pedidoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(paqueteDTOS);
    }
    public ResponseEntity<List<PedidoDTO>> getPaquetesByDriver (DriverDTO driverDTO) {
        if (driverDTO == null) {
            throw new RuntimeException("Driver es nulo");
        }
        List<Pedido> paquetes = pedidoService.findPedidosByDriverId(driverDTO.getId());
        // re clean esta linea
        List<PedidoDTO> paqueteDTOS = paquetes.stream().map(pedidoMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(paqueteDTOS);
    }
    // reveer esta func, cargar muchos paquetes de manera particular, puede ser excel y guarda en db,
    // hay que ver que info nos mandamos desde el front
    public ResponseEntity<List<PedidoDTO>> cargarPaquetesParticular(@RequestBody List<PedidoDTO> pedidoDTOS) {
        List<Pedido> paquetes = new ArrayList<>();
        for (int i = 0; i < pedidoDTOS.size(); i++) {
            Vendedor vendedor = vendedorService.findById(pedidoDTOS.get(i).getClienteId());
            Pedido pedido = new PedidoParticular(pedidoDTOS.get(i).getContenido(),
                    vendedor,
                    pedidoDTOS.get(i).getUbicacionEntrega(),
                    localService.getById(pedidoDTOS.get(i).getLocalId()));
            paquetes.add(pedido);
            pedidoService.save(pedido);
        }
        return ResponseEntity.ok(paqueteDTOS);
    }

    public ResponseEntity<List<PaqueteDTO>> cargarPaquetesMELI(@RequestBody List<PaqueteDTO> paqueteDTOS) {
        List<Paquete> paquetes = new ArrayList<>();
        for (int i = 0; i < paqueteDTOS.size(); i++) {
            Cliente cliente = clienteService.findById(paqueteDTOS.get(i).getClienteId());
            Paquete paquete = new Paquete(paqueteDTOS.get(i).getContenido(),
                    cliente,
                    TipoPaquete.MercadoLibre,
                    paqueteDTOS.get(i).getUbicacionEntrega(),
                    localService.getById(paqueteDTOS.get(i).getLocalId()));
            paquetes.add(paquete);
            paqueteService.save(paquete);
        }
        return ResponseEntity.ok(paqueteDTOS);
    }
    // cuando el driver escanea el qr de flex, cambia el estado a que lo tiene el driver,
    // ya esta en el sistema
    public ResponseEntity<PaqueteDTO> marcarPaqueteDespachado (PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteService.getPaqueteById(paqueteDTO.getId());
        paquete.setEstadoPaquete(EstadoPaquete.Despachado);
        paqueteService.save(paquete);
        // ver si crear viaje aca o no
        return ResponseEntity.ok(paqueteMapper.toDTO(paquete));
    }
}