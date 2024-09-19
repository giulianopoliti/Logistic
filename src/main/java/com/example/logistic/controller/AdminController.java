package com.example.logistic.controller;

import com.example.logistic.mapper.AdminMapper;
import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.roles.*;
import com.example.logistic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private TenantService tenantService;
    @Autowired
    private UsuarioService usuarioService;
    @PreAuthorize("hasRole('SUPERADMIN')")
    @PostMapping("/registro")
    public ResponseEntity<AdminDTO> crearAdmin (@RequestBody Map<String, Object> adminData) {
        try {
            Admin admin= adminService.createAdmin(adminData);
            return ResponseEntity.ok(adminMapper.toDTO(admin));
        }
        catch (InstantiationException | IllegalAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Paginacion
    /*
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Page<Usuario>> getAllUsersByTenant (@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(usuarioService.getAll(pageable));
    } // reveer esto,
*/

}
