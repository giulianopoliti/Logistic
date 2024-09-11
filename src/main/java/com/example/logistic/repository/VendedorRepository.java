package com.example.logistic.repository;

import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

    // Consulta personalizada utilizando JPQL
    @Query("SELECT c FROM Vendedor c WHERE c.tenant.id = :tenantId")
    List<Vendedor> findVendedoresByTenant(Long tenantId);
}
