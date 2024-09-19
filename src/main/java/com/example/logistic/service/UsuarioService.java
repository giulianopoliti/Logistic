package com.example.logistic.service;

import com.example.logistic.errors.ResourceNotFoundException;
import com.example.logistic.model.dtos.UsuarioDTOMini;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Usuario;
import com.example.logistic.repository.UsuarioRepository;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findByAuthId(String authId) {
        return usuarioRepository.findByAuthId(UUID.fromString(authId));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Método para crear el usuario adecuado basado en el JWT y el rol

    // Función genérica para crear usuarios

    public Usuario updateUserFromSupabase(DecodedJWT jwt) {
        Usuario usuario = usuarioRepository.findByAuthId(UUID.fromString(jwt.getSubject()));
        if (usuario != null) {
            // Actualizar campos que puedan haber cambiado en Supabase
            usuario.setEmail(jwt.getClaim("email").asString());
            usuario.setName(jwt.getClaim("name").asString());
            // Actualizar otros campos según sea necesario
            return usuarioRepository.save(usuario);
        }
        return null;
    }
    public List<UsuarioDTOMini> getAllUsersMini () {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTOMini> usuarioDTOMinis = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            UsuarioDTOMini usuarioDTOMini = new UsuarioDTOMini();
            usuarioDTOMini.setUuid(usuario.getAuthId());
            usuarioDTOMini.setName(usuario.getName());
            usuarioDTOMini.setRole(usuario.getRol());
            usuarioDTOMini.setActive(usuario.isActive());
            usuarios.set(i, usuario);
        }
        return usuarioDTOMinis;
    }
}
