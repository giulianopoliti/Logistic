package com.example.logistic.repository;

import com.example.logistic.model.roles.meli.IntegracionMeli;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IntegracionMeliRepository extends JpaRepository<IntegracionMeli, UUID> {
}
