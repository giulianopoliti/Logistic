package com.example.logistic.controller;

import com.example.logistic.mapper.ClienteMapper;
import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.model.dtos.ClienteDTO;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.paquete.EstadoPaquete;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.paquete.TipoPaquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Local;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.service.ClienteService;
import com.example.logistic.service.LocalService;
import com.example.logistic.service.PaqueteService;
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
public class PaqueteController {
    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private LocalService localService;

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteDTO> getPaqueteDTO(@PathVariable Integer id) {
        return ResponseEntity.ok(paqueteMapper.toDTO(paqueteService.getPaqueteById(id)));
    }

    // on click en paquete, que te muestre todo
    public ResponseEntity<Paquete> getPaquete (@RequestParam PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteService.getPaqueteById(paqueteDTO.getId());
        return ResponseEntity.ok(paquete);
    }
    public ResponseEntity<PaqueteDTO> cargarPaqueteParticular(String contenido,
                                                           ClienteDTO clienteDTO,
                                                           Ubicacion ubicacionEntrega,
                                                           Local local) {
        Paquete paquete = new Paquete(contenido, clienteService.findById(clienteDTO.getId()), TipoPaquete.Particular, ubicacionEntrega, local);
        paqueteService.save(paquete);
        return ResponseEntity.ok(paqueteMapper.toDTO(paquete));
    }
    public ResponseEntity<PaqueteDTO> cargarPaqueteML (String contenido,
                                                       ClienteDTO clienteDTO,
                                                       Ubicacion ubicacionEntrega,
                                                       Local local) {
        Paquete paquete = new Paquete(contenido, clienteService.findById(clienteDTO.getId()), TipoPaquete.MercadoLibre, ubicacionEntrega, local);
        paqueteService.save(paquete);
        return ResponseEntity.ok(paqueteMapper.toDTO(paquete));
    }

    // Driver marca cuando entrega un paquete
    public ResponseEntity<PaqueteDTO> marcarPaqueteEntregado (@RequestParam PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteMapper.toEntity(paqueteDTO);
        paquete.marcarPaqueteEntregado();
        paqueteService.save(paquete);
        return ResponseEntity.ok(paqueteMapper.toDTO(paquete));
    }

    // El driver puede marcar cuando no llega a entregar un paquete

    public ResponseEntity<List<PaqueteDTO>> getPaquetesCliente (@RequestBody ClienteDTO clienteDTO,
                                                                @RequestParam int page,
                                                                @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Paquete> paquetes = paqueteService.findPaquetesByClienteId(clienteDTO.getId(), pageable);
        List<PaqueteDTO> paqueteDTOS = paquetes.stream().map(paqueteMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(paqueteDTOS);
    }
    public ResponseEntity<List<PaqueteDTO>> getPaquetesByDriver (DriverDTO driverDTO) {
        if (driverDTO == null) {
            throw new RuntimeException("Driver es nulo");
        }
        List<Paquete> paquetes = paqueteService.findPaquetesByDriverId(driverDTO.getId());
        // re clean esta linea
        List<PaqueteDTO> paqueteDTOS = paquetes.stream().map(paqueteMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(paqueteDTOS);
    }
    // reveer esta func, cargar muchos paquetes de manera particular, puede ser excel y guarda en db,
    // hay que ver que info nos mandamos desde el front
    public ResponseEntity<List<PaqueteDTO>> cargarPaquetesParticular(@RequestBody List<PaqueteDTO> paqueteDTOS) {
        List<Paquete> paquetes = new ArrayList<>();
        for (int i = 0; i < paqueteDTOS.size(); i++) {
            Cliente cliente = clienteService.findById(paqueteDTOS.get(i).getClienteId());
            Paquete paquete = new Paquete(paqueteDTOS.get(i).getContenido(),
                    cliente,
                    TipoPaquete.Particular,
                    paqueteDTOS.get(i).getUbicacionEntrega(),
                    localService.getById(paqueteDTOS.get(i).getLocalId()));
            paquetes.add(paquete);
            paqueteService.save(paquete);
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