package com.example.logistic.controller;

import com.example.logistic.mapper.TenantMapper;
import com.example.logistic.model.dtos.TenantDTO;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    @Autowired
    private TenantService tenantService;
    @Autowired
    private TenantMapper tenantMapper;
    @GetMapping("/all")
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ResponseEntity<List<TenantDTO>> getAllTenants() {
        List<TenantDTO> tenantDTOS = tenantService.getAllTenants().stream().map(tenantMapper::toDto).toList();
        return ResponseEntity.ok(tenantDTOS);
    }
}
