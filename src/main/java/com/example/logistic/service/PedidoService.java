package com.example.logistic.service;

import com.example.logistic.mapper.PedidoMapper;
import com.example.logistic.model.dtos.PedidoDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Local;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.Ubicacion;
import com.example.logistic.model.ruta.paquete.EstadoPedido;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.model.ruta.paquete.PedidoMeli;
import com.example.logistic.model.ruta.paquete.PedidoParticular;
import com.example.logistic.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoMapper pedidoMapper;
    @Autowired
    private VendedorService vendedorService;

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


    public List<Pedido> findPedidosByDriverId(Long driverId) {
        List<Pedido> paquetes = new ArrayList<>();

        return paquetes;
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
    public PedidoDTO cargarPedidoParticular(String contenido,
                                      VendedorDTO vendedorDTO,
                                      Ubicacion ubicacionEntrega,
                                      Local local,
                                      String compradorName) {
        Vendedor vendedor = vendedorService.findById(vendedorDTO.getId());

        Pedido pedido = new PedidoParticular(contenido, vendedor, ubicacionEntrega, local.getUbicacion(), vendedor.getTenant(), compradorName);
        save(pedido);
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
        Page<Pedido> pedidosPage = pedidoRepository.findPedidosByVendedorId(vendedor.getId(), pageable);

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
}

