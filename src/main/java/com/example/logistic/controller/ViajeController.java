package com.example.logistic.controller;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.mapper.ViajeMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.dtos.ViajeDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.service.DriverService;
import com.example.logistic.service.PaqueteService;
import com.example.logistic.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viajes")
public class ViajeController {
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private ViajeMapper viajeMapper;
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private PaqueteMapper paqueteMapper;
    public ResponseEntity<Viaje> crearViaje (@RequestParam DriverDTO driverDTO, @RequestParam PaqueteDTO paqueteDTO) {
        Paquete paquete = paqueteMapper.toEntity(paqueteDTO);
        Driver driver = driverMapper.toEntity(driverDTO);
        Viaje viaje = new Viaje(driver, paquete);
        viajeService.save(viaje);
        return ResponseEntity.ok(viaje);
    }
    public ResponseEntity<ViajeDTO> cambiarOrden (@RequestParam ViajeDTO viajeDTO, @RequestParam Integer orden){
        Viaje viaje = viajeService.findById(viajeDTO.getId());
        viaje.setOrden(orden);
        return ResponseEntity.ok(viajeMapper.toDTO(viaje));
    }

    // cuando el driver marca entregado un paquete, tiene que ejecutar esta y entregarPaquete de PaqueteController
    public ResponseEntity<ViajeDTO> completarViaje (@RequestParam ViajeDTO viajeDTO) {
        Viaje viaje = viajeService.findById(viajeDTO.getId());
        viaje.setCompletado(viajeDTO.isCompletado());
        viajeService.save(viaje);
        return ResponseEntity.ok(viajeDTO);
    }

    public ResponseEntity<List<ViajeDTO>> getViajesByDriver (DriverDTO driverDTO) {
        if (driverDTO == null) {
            throw new RuntimeException("Driver es nulo");
        }
        List<Viaje> viajes = viajeService.findViajesByDriverId(driverDTO.getId());
        List<ViajeDTO> viajeDTOS = viajes.stream().map(viajeMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(viajeDTOS);
    }
    public ResponseEntity<List<ViajeDTO>> getViajesByDriverHoy(DriverDTO driverDTO) {
        if (driverDTO == null) {
            throw new RuntimeException("Driver es nulo");
        }
        List<ViajeDTO> viajeDTOS = new ArrayList<>();
        List<Viaje> viajes = viajeService.findViajesByDriverIdHoy(driverDTO.getId(), new Date());
        for (int i = 0; i < viajes.size(); i++) {
            viajeDTOS.add(viajeMapper.toDTO(viajes.get(i)));
        }
        return ResponseEntity.ok(viajeDTOS);
    }
}
