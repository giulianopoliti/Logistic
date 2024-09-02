package com.example.logistic.repository;

import com.example.logistic.model.paquete.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaqueteRepository extends JpaRepository<Paquete, Integer> {
}
