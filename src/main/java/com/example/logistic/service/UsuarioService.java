package com.example.logistic.service;

import com.example.logistic.model.roles.Usuario;
import com.example.logistic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> getAll (Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }
}
