package com.example.logistic.repository;

import com.example.logistic.model.roles.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
