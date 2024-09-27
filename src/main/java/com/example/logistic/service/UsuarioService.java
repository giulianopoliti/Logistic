package com.example.logistic.service;

import com.example.logistic.errors.ResourceNotFoundException;
import com.example.logistic.mapper.UsuarioMapper;
import com.example.logistic.model.dtos.UsuarioDTO;
import com.example.logistic.model.dtos.UsuarioDTOMini;
import com.example.logistic.model.roles.*;
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
    @Autowired
    private UsuarioMapper usuarioMapper;

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
            usuarioDTOMini.setRole(usuario.getClass().toString());
            usuarioDTOMini.setActive(usuario.isActive());
            usuarioDTOMinis.add(usuarioDTOMini);
        }
        return usuarioDTOMinis;
    }

    public UsuarioDTO createUsuario(Map<String, Object> dataUser) {
        if (dataUser.isEmpty()) {
            throw new RuntimeException("No se han enviado datos");
        }

        Object roleObj = dataUser.get("role");
        if (roleObj == null) {
            throw new RuntimeException("El campo 'role' es obligatorio");
        }

        String role = ((String) roleObj).toLowerCase();
        Usuario usuario;

        switch (role) {
            case "vendedor":
                usuario = new Vendedor();
                break;
            case "driver":
                usuario = new Driver();
                break;
            case "operadordeposito":
                usuario = new OperadorDeposito();
                break;
            case "admin":
                usuario = new Admin();
                break;
            default:
                throw new IllegalArgumentException("Rol no válido: " + role);
        }

        UsuarioDTO usuarioDTO = setCommonFields(dataUser, usuario);
        return usuarioDTO;
    }
    public UsuarioDTO setCommonFields (Map<String, Object> dataUser, Usuario usuario) {
        usuario.setAuthId(UUID.fromString((String) dataUser.get("authId")));
        usuario.setName((String) dataUser.get("name"));
        usuario.setLastName((String) dataUser.get("lastName"));
        usuario.setAddress((String) dataUser.get("address"));
        usuario.setEmail((String) dataUser.get("email"));
        usuario.setCuil((String) dataUser.get("cuil"));
        usuario.setPhone((String) dataUser.get("phone"));
        usuario.setEmergencyPhone((String) dataUser.get("emergencyPhone"));
        usuario.setUsername((String) dataUser.get("username"));
        usuario.setProfilePictureURL((String) dataUser.get("profilePictureURL"));
        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario, usuario.getClass().toString());
        return usuarioDTO;
    }

    /*
        @PostMapping("/registro")
    public VendedorDTO createVendedor(Map<String, Object> vendedorData) throws InstantiationException, IllegalAccessException, ParseException {
        Vendedor vendedor = new Vendedor();
        vendedor.setAuthId(UUID.fromString((String) vendedorData.get("authId")));
        vendedor.setName((String) vendedorData.get("name"));
        vendedor.setLastName((String) vendedorData.get("lastName"));
        vendedor.setAddress((String) vendedorData.get("address"));
        vendedor.setEmail((String) vendedorData.get("email"));
        vendedor.setCuil((String) vendedorData.get("cuil"));
        Tenant tenant = tenantService.getByUuid(UUID.fromString((String) vendedorData.get("tenantId")));
        vendedor.setTenant(tenant);
        String dateStr = (String) vendedorData.get("dateOfBirth");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirth = dateFormat.parse(dateStr);
        vendedor.setDateOfBirth(dateOfBirth);        return vendedorMapper.toDTO(save(vendedor));
    }
     */
}
