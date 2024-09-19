package com.example.logistic.controller;

import com.example.logistic.mapper.AdminMapper;
import com.example.logistic.model.dtos.AdminDTO;
import com.example.logistic.model.dtos.UsuarioDTO;
import com.example.logistic.model.dtos.UsuarioDTOMini;
import com.example.logistic.model.roles.Usuario;
import com.example.logistic.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTOMini>> getAllUsers() {
        List<UsuarioDTOMini> usuariosDTOMini = usuarioService.getAllUsersMini();
        System.out.println(usuariosDTOMini);
        return ResponseEntity.ok(usuariosDTOMini);
    }
}
