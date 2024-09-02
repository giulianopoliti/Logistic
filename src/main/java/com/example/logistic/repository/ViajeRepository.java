package com.example.logistic.repository;

import com.example.logistic.mapper.ViajeMapper;
import com.example.logistic.model.ruta.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
}
