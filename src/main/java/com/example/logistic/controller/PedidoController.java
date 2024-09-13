package com.example.logistic.controller;


import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.paquete.EstadoPedido;

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
@RequestMapping("/api/pedidos")
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
    public ResponseEntity<PedidoDTO> getPedidoDTO(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoMapper.toDTO(pedidoService.getPedidoById(id)));
    }

    public ResponseEntity<PedidoDTO> cargarPaqueteParticular(String contenido,
                                                           VendedorDTO vendedorDTO,
                                                           Ubicacion ubicacionEntrega,
                                                           Local local,
                                                             String compradorName) {

        PedidoDTO pedidoDTO = pedidoService.cargarPedidoParticular(contenido, vendedorDTO, ubicacionEntrega, local, compradorName);
        return ResponseEntity.ok(pedidoDTO);
    }
    public ResponseEntity<PedidoDTO> cargarPedidoMeli (String contenido,
                                                       VendedorDTO vendedorDTO,
                                                       Ubicacion ubicacionEntrega,
                                                       Local local,
                                                      String orderId,
                                                      String sellerId,
                                                      String compradorName) {
        PedidoDTO pedidoDTO = pedidoService.cargarPedidoMeli(contenido, vendedorDTO, ubicacionEntrega, local, orderId, sellerId, compradorName);
        return ResponseEntity.ok(pedidoDTO);
    }

    // Driver marca cuando entrega un paquete
    public ResponseEntity<PedidoDTO> marcarPaqueteEntregado (@RequestParam PedidoDTO pedidoDTO) {
        PedidoDTO pedido = pedidoService.marcarPaqueteEntregado(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    // El driver puede marcar cuando no llega a entregar un paquete

    public ResponseEntity<Page<PedidoDTO>> getPaquetesVendedor (@RequestBody VendedorDTO vendedorDTO,
                                                               @RequestParam int page,
                                                               @RequestParam int size) {
        Page<PedidoDTO> pedidoDTOS = pedidoService.findPedidosByVendedor(vendedorDTO, PageRequest.of(page, size));
        return ResponseEntity.ok(pedidoDTOS);
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
        pedidoService.cargarPedidosParticular(pedidoDTOS);
        return ResponseEntity.ok(pedidoDTOS);
    }

    public ResponseEntity<List<PedidoDTO>> cargarPaquetesMELI(@RequestBody List<PedidoDTO> pedidoDTOS) {
        pedidoService.cargarPedidosMeli(pedidoDTOS); // falta terminarlo
        return ResponseEntity.ok(pedidoDTOS);
    }
    // cuando el driver escanea el qr de flex, cambia el estado a que lo tiene el driver,
    // ya esta en el sistema
    public ResponseEntity<PedidoDTO> marcarPaqueteDespachado (PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.getPedidoById(pedidoDTO.getId());
        pedido.setEstadoPedido(EstadoPedido.DESPACHADO);
        pedidoService.save(pedido);
        // ver si crear viaje aca o no
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }
}