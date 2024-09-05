package com.example.logistic.repository;

import com.example.logistic.model.roles.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
