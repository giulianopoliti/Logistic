package com.example.logistic.repository;

import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Cliente;
import com.example.logistic.model.roles.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PaqueteRepository extends JpaRepository<Paquete, Integer> {
    @Query("SELECT p FROM Paquete p WHERE p.cliente.id = :clienteId")
    List<Paquete> findPaquetesByClienteId(Integer clienteId);

}
