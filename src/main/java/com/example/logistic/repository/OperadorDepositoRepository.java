package com.example.logistic.repository;

import com.example.logistic.model.roles.OperadorDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OperadorDepositoRepository extends JpaRepository<OperadorDeposito, UUID> {
    OperadorDeposito getById(UUID uuid);
}
