package com.example.logistic.repository;


import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.paquete.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT p FROM Pedido p WHERE p.vendedor.id = :vendedorId")
    Page<Pedido> findPedidosByVendedorId(Long vendedorId, Pageable pageable);
    @Query("SELECT p FROM Pedido p WHERE p.vendedor.tenant = :tenant")
    Page<Pedido> findPedidosByTenant(Tenant tenant, Pageable pageable);

}
