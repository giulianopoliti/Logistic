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
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/registro")
    public ResponseEntity<AdminDTO> crearAdmin (@RequestBody Map<String, Object> adminData) {
        return null;
    }

    // Paginacion
    @GetMapping("/{id}")
    public ResponseEntity<Page<Usuario>> getAllUsersByTenant (@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseEntity.ok(usuarioService.getAll(pageable));
    } // reveer esto,


}
