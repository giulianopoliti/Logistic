package com.example.logistic.service;

import com.example.logistic.model.roles.Tenant;
import com.example.logistic.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    public Tenant getById (Integer id) {
        return tenantRepository.getById(id);
    }
}
