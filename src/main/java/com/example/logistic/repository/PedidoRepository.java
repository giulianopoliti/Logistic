package com.example.logistic.repository;


import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.ruta.paquete.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    @Query("SELECT p FROM Pedido p WHERE p.vendedor.authId = :vendedorAuthId")
    Page<Pedido> findPedidosByVendedorId(UUID vendedorAuthId, Pageable pageable);
    @Query("SELECT p FROM Pedido p WHERE p.vendedor.tenant = :tenant")
    Page<Pedido> findPedidosByTenant(Tenant tenant, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.fechaCreacion = :date")
    Page<Pedido> findPedidosByDate(Date date, Pageable pageable);
    @Query("SELECT p FROM Pedido p WHERE p.fechaCreacion = :date")
    List<Pedido> findPedidosByDateForMap (Date date);
    @Query("SELECT p FROM Pedido p WHERE p.driver.authId = :driverAuthId")
    Page<Pedido> findPedidosByDriverId(UUID driverAuthId, Pageable pageable);
}
