package com.example.logistic.repository;

import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    // Consulta personalizada utilizando JPQL
    @Query("SELECT c FROM Cliente c WHERE c.tenant = :tenant")
    List<Cliente> findClientesByTenant(Tenant tenant);
}
