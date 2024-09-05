package com.example.logistic.controller;

import com.example.logistic.mapper.AdminMapper;
import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Admin;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.model.roles.Role;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;
    private AsignacionService asignacionService;
    @Autowired
    private TenantService tenantService;
    @PostMapping
    public ResponseEntity<AdminDTO> crearAdmin (@RequestBody Map<String, Object> adminData) {
        Tenant tenant = tenantService.getById((Integer) adminData.get("tenantId"));
        // aca deberiamos encriptar la contraseña
        Admin admin = new Admin((String)adminData.get("name"),
                (String)adminData.get("lastName"),
                (Date) adminData.get("dateOfBirth"),
                tenant,
                (String)adminData.get("email"),
                (String)adminData.get("username"),
                (String)adminData.get("password"),
                (Role)adminData.get("role")); // reveer esto
        adminService.save(admin);
        return ResponseEntity.ok(adminMapper.toDTO(admin));
    }


}
