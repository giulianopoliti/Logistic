package com.example.logistic.repository;

import com.example.logistic.model.roles.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    Tenant getById(UUID uuid);
    Tenant findFirstByOrderByUuidAsc();
}
