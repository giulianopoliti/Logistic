package com.example.logistic.service;

import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.mapper.RutaMapper;
import com.example.logistic.model.dtos.RutaDTO;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.Ruta;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RutaService {
    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private DriverService driverService;
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private RutaMapper rutaMapper;
    @Autowired
    private TenantService tenantService;

    public Ruta getRutaById(Long id) {
        return rutaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruta no encontrada"));
    }

    public Ruta save(Ruta ruta) {
        return rutaRepository.save(ruta);
    }

    public Ruta actualizarViajes(Long rutaId, List<Pedido> pedidos) {
        Ruta ruta = getRutaById(rutaId);
        ruta.setPedidos(pedidos);
        return rutaRepository.save(ruta);
    }
    public Ruta findByDriverId(UUID driverId) {
        return rutaRepository.findByDriverId(driverId, new Date()); // esto te trae la primera ruta que encuentra del driver
    }
    public List<Ruta> findByDay (Date date, Long tenantId) {
        return rutaRepository.findByDay(date, tenantId);
    }

    public RutaDTO crearRuta(RutaDTO rutaDTO) {
        Driver driver = driverService.getDriverById(rutaDTO.getDriverId());
        Tenant tenant = tenantService.getById(rutaDTO.getTenantId());
        List<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < rutaDTO.getPedidoDTOS().size(); i++) {
            Pedido pedido = pedidoService.getPedidoById(rutaDTO.getPedidoDTOS().get(i).getId());
            pedidos.add(pedido);
        }
        Ruta ruta = new Ruta(driver, pedidos, tenant);
        rutaRepository.save(ruta);
        return rutaMapper.toDTO(ruta);
    }
}
