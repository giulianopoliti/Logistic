package com.example.logistic.service;

import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.PedidoMapaDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Local;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.model.ruta.paquete.PedidoMeli;
import com.example.logistic.model.ruta.paquete.PedidoParticular;
import com.example.logistic.repository.PedidoRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private LocalService localService;

    public Pedido getPedidoById(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Paquete no encontrado"));
    }

    public Pedido actualizarEstadoPedido(Long id, EstadoPedido estado) {
        Pedido pedido = getPedidoById(id);
        pedido.setEstadoPedido(estado);
        return pedidoRepository.save(pedido);
    }
    public Pedido save (Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    /*public Page<PedidoRepository> findPaquetesByClienteId (Integer clienteId, Pageable pageable){
        return pedidoRepository.findPaquetesByClienteId(clienteId, pageable);
    }*/


    public Page<Pedido> findPedidosByDriverId(UUID driverId, Pageable pageable) {
        return pedidoRepository.findPedidosByDriverId(driverId, pageable);
    }
    public PedidoDTO cargarPedidoMeli(String contenido,
                                      VendedorDTO vendedorDTO,
                                      Ubicacion ubicacionEntrega,
                                      Local local,
                                      String orderId,
                                      String sellerId,
                                      String compradorName) {
        Vendedor vendedor = vendedorService.findById(vendedorDTO.getId());

        Pedido pedido = new PedidoMeli(contenido, vendedor, ubicacionEntrega,local.getUbicacion(), vendedor.getTenant(), orderId, sellerId, compradorName);
        save(pedido);
        return pedidoMapper.toDTO(pedido);
    }
    public PedidoDTO cargarPedidoParticular(Map<String, Object> pedidoData) {
        // Obtener valores del Map con verificación de null
        String contenido = (String) pedidoData.get("contenido");
        System.out.println(contenido);

        Map<String, Object> ubicacionData = (Map<String, Object>) pedidoData.get("ubicacionEntrega");
        Double latitud = ubicacionData != null ? (Double) ubicacionData.get("latitud") : null;
        Double longitud = ubicacionData != null ? (Double) ubicacionData.get("longitud") : null;
        System.out.println(latitud + longitud);

        Number vendedorIdNumber = (Number) pedidoData.get("vendedorId");
        UUID vendedorId = vendedorIdNumber != null ? UUID.fromString(vendedorIdNumber.toString()) : null;
        System.out.println(vendedorId);

        Number localIdNumber = (Number) pedidoData.get("localId");
        Long localId = localIdNumber != null ? localIdNumber.longValue() : null;
        System.out.println(localId);

        String compradorName = (String) pedidoData.get("compradorName");
        System.out.println(compradorName);


        // Verificación de null para latitud y longitud
        if (latitud == null || longitud == null) {
            throw new RuntimeException("Latitud y longitud son requeridas en la ubicacionEntrega");
        }

        // Crear la instancia de Ubicacion
        Ubicacion ubicacionEntrega = new Ubicacion(latitud, longitud);

        // Buscar Local y Vendedor
        Local local = localService.findById(localId);
        Vendedor vendedor = vendedorService.findById(vendedorId);

        if (vendedor == null) {
            throw new RuntimeException("Vendedor no encontrado con id: " + vendedorId);
        }
        if (local == null) {
            throw new RuntimeException("Local no encontrado con id: " + localId);
        }

        // Crear el pedido y guardarlo
        Pedido pedido = new PedidoParticular(contenido, vendedor, ubicacionEntrega, local.getUbicacion(), vendedor.getTenant(), compradorName);
        save(pedido);

        // Retornar el DTO del pedido
        return pedidoMapper.toDTO(pedido);
    }

    public PedidoDTO marcarPaqueteEntregado (PedidoDTO pedidoDTO) {
        Pedido pedido = getPedidoById(pedidoDTO.getId());
        pedido.setEstadoPedido(EstadoPedido.ENTREGADO);
        return pedidoMapper.toDTO(save(pedido));
    }
    public Page<PedidoDTO> findPedidosByVendedor(VendedorDTO vendedorDTO, Pageable pageable) {
        Vendedor vendedor = vendedorService.findById(vendedorDTO.getId());

        // Usar la paginación en la consulta
        Page<Pedido> pedidosPage = pedidoRepository.findPedidosByVendedorId(vendedor.getAuthId(), pageable);

        // Mapear la entidad Pedido a PedidoDTO
        return pedidosPage.map(pedido -> pedidoMapper.toDTO(pedido));
    }
    public List<PedidoDTO> cargarPedidosParticular (List<PedidoDTO> pedidoDTOS) {
        for (int i = 0; i < pedidoDTOS.size(); i++) {
            Vendedor vendedor = vendedorService.findById(pedidoDTOS.get(i).getVendedorId());
            Pedido pedido = new PedidoParticular(pedidoDTOS.get(i).getContenido(), vendedor, pedidoDTOS.get(i).getUbicacionEntrega(), pedidoDTOS.get(i).getUbicacionActual(), vendedor.getTenant(), pedidoDTOS.get(i).getCompradorName());
            save(pedido);
        }
        return pedidoDTOS;
    }
    public List<PedidoDTO> cargarPedidosMeli (List<PedidoDTO> pedidoDTOS) {
        for (int i = 0; i < pedidoDTOS.size(); i++) {
            Vendedor vendedor = vendedorService.findById(pedidoDTOS.get(i).getVendedorId());
            // logica para entrar datos de meli
        }
        return pedidoDTOS;
    }
    public Page<PedidoDTO> findPedidosByDate (String date, Pageable pageable) {
        Date parsedDate = formatDate(date);
        Page<Pedido> pedidos = pedidoRepository.findPedidosByDate(parsedDate, pageable);
        return pedidos.map(pedido -> pedidoMapper.toDTO(pedido));
    }
    public List<PedidoMapaDTO> findPedidosByDateForMap (String date) {
        Date parsedDate = formatDate(date);
        List<Pedido> pedidos = pedidoRepository.findPedidosByDateForMap(parsedDate);
        List<PedidoMapaDTO> pedidosMapaDTO = new ArrayList<>();
        for (int i = 0; i < pedidos.size(); i++) {
            PedidoMapaDTO pedidoMapaDTO = new PedidoMapaDTO();
            pedidoMapaDTO.setUbicacionEntrega(pedidos.get(i).getUbicacionEntrega());
            pedidoMapaDTO.setEstadoPedido(pedidos.get(i).getEstadoPedido());
            pedidoMapaDTO.setId(pedidos.get(i).getId());
            pedidosMapaDTO.add(pedidoMapaDTO);
        }
        return pedidosMapaDTO;
    }
    private Date formatDate (String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        try {
            parsedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException("Fecha invalida"); // Manejo de error si el formato de fecha es incorrecto
        }
        return parsedDate;
    }
}

