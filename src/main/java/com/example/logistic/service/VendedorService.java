package com.example.logistic.service;

import com.example.logistic.mapper.VendedorMapper;
import com.example.logistic.model.dtos.TenantDTO;
import com.example.logistic.model.dtos.VendedorDTO;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import com.example.logistic.model.ruta.paquete.Pedido;
import com.example.logistic.repository.PedidoRepository;
import com.example.logistic.repository.VendedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private VendedorMapper vendedorMapper;

    public Vendedor findById(Long id) {
        return vendedorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public VendedorDTO findByIdAsDTO(Long id) {
        return vendedorMapper.toDTO(findById(id));
    }
    public List<Vendedor> findVendedoresByTenant (TenantDTO tenantDTO) {
        List<Vendedor> vendedor = vendedorRepository.findVendedoresByTenant(tenantDTO.getId());
        return vendedor;
    }

    public Vendedor save(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public List<Pedido> findPaquetesPorCliente(Long vendedorId) {
        Vendedor vendedor = findById(vendedorId);
        return vendedor.getPedidos();
    }

    public Pedido createPaquete(Long vendedorId, Pedido pedido) {
        Vendedor vendedor = findById(vendedorId);
        pedido.setVendedor(vendedor);
        return pedidoRepository.save(pedido);
    }
    public Vendedor crearVendedorConAcceso(Map<String, Object> clienteData) {

        Tenant tenant = tenantService.getById((Integer) clienteData.get("tenantId"));

        // Crea la entidad Vendedor
        Vendedor vendedor = new Vendedor(
                (String) clienteData.get("name"),
                (String) clienteData.get("lastName"),
                (Date) clienteData.get("dateOfBirth"),
                tenant,
                (String) clienteData.get("email"),
                (String) clienteData.get("username"),
                (String) clienteData.get("password")
        );

        // Guarda la entidad en la base de datos
        vendedorRepository.save(vendedor);

        // Convierte la entidad guardada a DTO y la retorna
        return vendedor;
    }
}