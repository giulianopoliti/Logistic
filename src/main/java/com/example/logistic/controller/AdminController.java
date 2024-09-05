package com.example.logistic.controller;

import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.dtos.DriverDTO;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    private AsignacionService asignacionService;
}
