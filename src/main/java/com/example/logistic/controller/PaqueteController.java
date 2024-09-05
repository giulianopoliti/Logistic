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
import com.example.logistic.service.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<PaqueteDTO> getPaquete(@PathVariable Integer id) {
        return ResponseEntity.ok(paqueteMapper.toDTO(paqueteService.getPaqueteById(id)));
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
    public ResponseEntity<PaqueteDTO> marcarPendienteProximoDia (PaqueteDTO paqueteDTO){
        Paquete paquete = paqueteMapper.toEntity(paqueteDTO);
        paquete.marcarPendienteProximoDia();
        paqueteService.save(paquete);
        return ResponseEntity.ok(paqueteMapper.toDTO(paquete));
    }
    public ResponseEntity<List<PaqueteDTO>> getPaquetesCliente (ClienteDTO clienteDTO) {
        List<Paquete> paquetes = paqueteService.findPaquetesByClienteId(clienteDTO.getId());
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
}