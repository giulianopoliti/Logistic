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
import com.example.logistic.model.ruta.paquete.PedidoMeli;
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
    public ResponseEntity<PedidoDTO> getPedidoDTO(@PathVariable Integer id) {
        return ResponseEntity.ok(pedidoMapper.toDTO(pedidoService.getPedidoById(id)));
    }

    // on click en paquete, que te muestre todo
    public ResponseEntity<Pedido> getPaquete (@RequestParam PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.getPedidoById(pedidoDTO.getId());
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
        Pedido pedido = new PedidoParticular();
        pedido.setContenido(contenido);
        Vendedor vendedor = vendedorService.findById(vendedorDTO.getId());
        pedido.setVendedor(vendedor);
        pedidoService.save(pedido);
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    } // completar func

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
        List<Pedido> paquetes = pedidoService.findPedidosByDriverId(vendedorDTO.getId());
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
            Pedido pedido = new PedidoParticular();
            paquetes.add(pedido);
            pedidoService.save(pedido);
        }
        return ResponseEntity.ok(pedidoDTOS);
    }

    public ResponseEntity<List<PedidoDTO>> cargarPaquetesMELI(@RequestBody List<PedidoDTO> pedidoDTOS) {
        List<Pedido> paquetes = new ArrayList<>();
        for (int i = 0; i < pedidoDTOS.size(); i++) {
            Vendedor vendedor = vendedorService.findById(pedidoDTOS.get(i).getClienteId());
            Pedido pedido = new PedidoMeli();
            pedidoService.save(pedido);
        }
        return ResponseEntity.ok(pedidoDTOS);
    }
    // cuando el driver escanea el qr de flex, cambia el estado a que lo tiene el driver,
    // ya esta en el sistema
    public ResponseEntity<PedidoDTO> marcarPaqueteDespachado (PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.getPedidoById(pedidoDTO.getId());
        pedido.setEstadoPaquete(EstadoPaquete.Despachado);
        pedidoService.save(pedido);
        // ver si crear viaje aca o no
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }
}