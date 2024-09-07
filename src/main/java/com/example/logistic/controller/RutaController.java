package com.example.logistic.controller;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.mapper.RutaMapper;
import com.example.logistic.mapper.ViajeMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.dtos.ViajeDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.service.DriverService;
import com.example.logistic.service.PaqueteService;
import com.example.logistic.service.RutaService;
import com.example.logistic.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaController {
    @Autowired
    private RutaService rutaService;
    @Autowired
    private ViajeController viajeController;
    @Autowired
    private ViajeService viajeService;
    @Autowired
    private ViajeMapper viajeMapper;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private RutaMapper rutaMapper;
    @Autowired
    private PaqueteService paqueteService;
    @Autowired
    private DriverService driverService;

    @GetMapping("/{id}")
    public ResponseEntity<Ruta> getRutaByDriver(@PathVariable Integer id) {
        // Implementación
        return null;
    }

    @PostMapping
    public ResponseEntity<RutaDTO> crearRuta(@RequestParam List<PaqueteDTO> paquetesDTO,
                                          DriverDTO driverDTO) {
        List<Paquete> paquetes = paquetesDTO.stream()
                .map(paqueteDTO -> paqueteService.getPaqueteById(paqueteDTO.getId()))
                .collect(Collectors.toList());
        Driver driver = driverService.getDriverById(driverDTO.getId());
        Ruta ruta = new Ruta(driver, paquetes);
        rutaService.save(ruta);
        return ResponseEntity.ok(rutaMapper.toDTO(ruta)); // REVEER COMO MAPEA DE RUTA A RUTADTO
    }

    @PutMapping("/{id}/viajes")
    public ResponseEntity<Ruta> actualizarViajes(@PathVariable Integer id, @RequestBody List<Viaje> viajes) {
        // Implementación
        return null;

    }

    @PutMapping("/{id}/")
    public ResponseEntity<Ruta> addViaje (@RequestParam DriverDTO driverDTO, PaqueteDTO paqueteDTO) {
        Ruta ruta = rutaService.findByDriverId(driverDTO.getId());
        ruta.addViaje(driverMapper.toEntity(driverDTO), paqueteMapper.toEntity(paqueteDTO));
        return ResponseEntity.ok(ruta);
    }
    public ResponseEntity<Ruta> finalizarRuta (DriverDTO driverDTO) {
        Ruta ruta = rutaService.findByDriverId(driverDTO.getId());
        if (ruta.isCompletada()) {
            return ResponseEntity.ok(ruta);
        } else return null; // reveer esto, que rta dar
    }
    public ResponseEntity<List<RutaDTO>> getRutasByDay (@RequestParam Date date, @RequestParam Tenant tenant) {
        List<Ruta> rutasDay = rutaService.findByDay(date, tenant.getId());
        List<RutaDTO> rutasDTO = rutasDay.stream().map(rutaMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(rutasDTO);
    }
    // ver detalle cuando hace click en una ruta. Podriamos manejarlo con mostrar todos los envios dto
    public ResponseEntity<Ruta> verDetalleRuta (@RequestParam RutaDTO rutaDTO) {
        Ruta ruta = rutaService.getRutaById(rutaDTO.getId());
        return ResponseEntity.ok(ruta);
    }
    // aca esta la otra forma
    public ResponseEntity<List<ViajeDTO>> verViajesDTOByRuta (@RequestParam RutaDTO rutaDTO) {
        Ruta ruta = rutaService.getRutaById(rutaDTO.getId());
        List<ViajeDTO> viajeDTOS = ruta.getViajes().stream().map(viajeMapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(viajeDTOS);
    }


}