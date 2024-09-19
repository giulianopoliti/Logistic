package com.example.logistic.controller;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.mapper.RutaMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.service.DriverService;
import com.example.logistic.service.PedidoService;
import com.example.logistic.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    private RutaService rutaService;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private RutaMapper rutaMapper;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> getRutaByDriver(@PathVariable Integer id) {
        // Implementación
        return null;
    }

    @PostMapping("/crear")
    public ResponseEntity<RutaDTO> crearRuta(@RequestBody RutaDTO rutaDTO) {
        RutaDTO nuevaRuta = rutaService.crearRuta(rutaDTO);
        return new ResponseEntity<>(nuevaRuta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/viajes")
    public ResponseEntity<Ruta> actualizarPedidos(@PathVariable Integer id, @RequestBody List<Pedido> pedidos) {
        // Implementación
        return null;
    }

    @PutMapping("/{id}/")
    public ResponseEntity<Ruta> addPedidoCreado (@RequestParam DriverDTO driverDTO, PedidoDTO pedidoDTO) {
        Ruta ruta = rutaService.findByDriverId(driverDTO.getUuid());
        Pedido pedido = pedidoService.getPedidoByUuid(pedidoDTO.getUuid());
        return ResponseEntity.ok(ruta);
    }
    public ResponseEntity<Ruta> finalizarRuta (DriverDTO driverDTO) {
        Ruta ruta = rutaService.findByDriverId(driverDTO.getUuid());
        if (ruta.isCompletada()) {
            return ResponseEntity.ok(ruta);
        } else return null; // reveer esto, que rta dar
    }
    public ResponseEntity<List<RutaDTO>> getRutasByDay (@RequestParam Date date, @RequestParam Tenant tenant) {
        List<Ruta> rutasDay = rutaService.findByDay(date, tenant.getUuid());
        List<RutaDTO> rutasDTO = rutasDay.stream().map(rutaMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(rutasDTO);
    }
    // ver detalle cuando hace click en una ruta. Podriamos manejarlo con mostrar todos los envios dto
    public ResponseEntity<Ruta> verDetalleRuta (@RequestParam RutaDTO rutaDTO) {
        Ruta ruta = rutaService.getRutaById(rutaDTO.getUuid());
        return ResponseEntity.ok(ruta);
    }
    // aca esta la otra forma
    /*public ResponseEntity<List<ViajeDTO>> verViajesDTOByRuta (@RequestParam RutaDTO rutaDTO) {
        Ruta ruta = rutaService.getRutaById(rutaDTO.getId());
        List<ViajeDTO> viajeDTOS = ruta.getPedidos().stream().map(::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(viajeDTOS);
    }*/


}