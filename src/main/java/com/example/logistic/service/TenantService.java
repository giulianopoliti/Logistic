package com.example.logistic.service;

import com.example.logistic.model.roles.Tenant;
import com.example.logistic.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    public Tenant getByUuid (UUID uuid) {
        return tenantRepository.getById(uuid);
    }
    public Tenant getDefaultTenant () {
        return tenantRepository.findFirstByOrderByUuidAsc();
    }
    public List<Tenant> getAllTenants () {
        return tenantRepository.findAll();
    }
}
