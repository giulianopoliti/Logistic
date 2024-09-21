package com.example.logistic.controller;


import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.PedidoMapaDTO;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasAnyRole;

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
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERADORDEPOSITO')")
    @GetMapping()
    public ResponseEntity<List<PedidoMapaDTO>> getPedidosMapaHoy (@RequestParam String date) {
        List<PedidoMapaDTO> pedidos = pedidoService.findPedidosByDateForMap(date);
        return ResponseEntity.ok(pedidos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getPedidoDTO(@PathVariable UUID uuid) {
        return ResponseEntity.ok(pedidoMapper.toDTO(pedidoService.getPedidoByUuid(uuid)));
    }
    @PostMapping("/cargar/particular")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPERADORDEPOSITO', 'VENDEDOR')")
    public ResponseEntity<PedidoDTO> cargarPaqueteParticular(@RequestBody Map<String, Object> pedidoData) {

        PedidoDTO pedidoDTO = pedidoService.cargarPedidoParticular(pedidoData);
        return ResponseEntity.ok(pedidoDTO);
    }
    /*public ResponseEntity<PedidoDTO> cargarPedidoMeli (@RequestBody Map<String, Object> pedidoData) {
        PedidoDTO pedidoDTO = pedidoService.cargarPedidoMeli(Map<String, Object> pedidoData);
        return ResponseEntity.ok(pedidoDTO);
    }*/

    // Driver marca cuando entrega un paquete
    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    public ResponseEntity<PedidoDTO> marcarPaqueteEntregado (@RequestParam PedidoDTO pedidoDTO) {
        PedidoDTO pedido = pedidoService.marcarPaqueteEntregado(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    // El driver puede marcar cuando no llega a entregar un paquete
    @PreAuthorize("hasAnyRole('ROLE_VENDEDOR', 'ADMIN')")
    public ResponseEntity<Page<PedidoDTO>> getPaquetesVendedor (@RequestBody UUID vendedorUuid,
                                                               @RequestParam int page,
                                                               @RequestParam int size) {
        Page<PedidoDTO> pedidoDTOS = pedidoService.findPedidosByVendedor(vendedorUuid, PageRequest.of(page, size));
        return ResponseEntity.ok(pedidoDTOS);
    }
    public ResponseEntity<List<PedidoDTO>> getPedidosByDriver (@RequestBody DriverDTO driverDTO,
                                                                @RequestParam int page,
                                                                @RequestParam int size) {
        if (driverDTO == null) {
            throw new RuntimeException("Driver es nulo");
        }
        Page<Pedido> paquetes = pedidoService.findPedidosByDriverId(driverDTO.getUuid(), PageRequest.of(page, size));
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
        Pedido pedido = pedidoService.getPedidoByUuid(pedidoDTO.getUuid());
        pedido.setEstadoPedido(EstadoPedido.DESPACHADO);
        pedidoService.save(pedido);
        // ver si crear viaje aca o no
        return ResponseEntity.ok(pedidoMapper.toDTO(pedido));
    }
    @GetMapping("/all")
    public ResponseEntity<Page<PedidoDTO>> getPedidosByDate (@RequestParam String date,
                                                             @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "30") int size) {
        Page<PedidoDTO> pedidoDTOS = pedidoService.findPedidosByDate(date, PageRequest.of(page, size));
        return ResponseEntity.ok(pedidoDTOS);
    }
}