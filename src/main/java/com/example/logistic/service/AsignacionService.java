package com.example.logistic.service;

import com.example.logistic.mapper.DriverMapper;
import com.example.logistic.mapper.PaqueteMapper;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.dtos.PaqueteDTO;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.ruta.Viaje;
import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignacionService {
    private final PedidoService pedidoService;
    private final DriverService driverService;
    private final RutaService rutaService;
    @Autowired
    private PaqueteMapper paqueteMapper;
    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    public AsignacionService(PedidoService pedidoService,
                             DriverService driverService,
                             RutaService rutaService) {
        this.pedidoService = pedidoService;
        this.driverService = driverService;
        this.rutaService = rutaService;
    }

    @Transactional
    public void asignarPaqueteADriver(PedidoDTO pedidoDTO, DriverDTO driverDTO) {
        Driver driver = driverService.getDriverById(driverMapper.toEntity(driverDTO).getId());
        Pedido pedido = pedidoService.getPaqueteById(paqueteMapper.toEntity(paqueteDTO).getId());
    }
}
