package com.example.logistic.service;

import com.example.logistic.errors.ResourceNotFoundException;
import com.example.logistic.model.roles.Tenant;
import com.example.logistic.model.roles.Usuario;
import com.example.logistic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService <T extends Usuario> {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TenantService tenantService;

    public Page<Usuario> getAll (Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }
    public Usuario findByAuthId(String authId) {
        return usuarioRepository.findByAuthId(UUID.fromString(authId));
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public T createUser(Map<String, Object> userData, Class<T> userType) throws InstantiationException, IllegalAccessException {
        // Verificar si el tenant existe
        Tenant tenant = tenantService.getById((Long) userData.get("tenantId"));
        if (tenant == null) {
            throw new ResourceNotFoundException("Tenant not found");
        }

        // Validar datos comunes
        UserUtils.validateUserData(userData);

        // Crear la instancia del tipo de usuario específico
        T user = userType.newInstance();

        // Setear los datos comunes
        user.setName((String) userData.get("name"));
        user.setLastName((String) userData.get("lastName"));
        user.setDateOfBirth((Date) userData.get("dateOfBirth"));
        user.setEmail((String) userData.get("email"));
        user.setPhone((String) userData.get("phone"));
        user.setUsername((String) userData.get("username"));
        user.setCuil((String) userData.get("cuil"));
        user.setAddress((String) userData.get("address"));
        if (user.getProfilePictureURL() != null) {
            user.setProfilePictureURL((String) userData.get("profilePictureURL"));
        }
        if (user.getEmergencyPhone() != null) {
            user.setEmergencyPhone((String) userData.get("emergencyPhone"));
        }
        user.setTenant(tenant);

        // Guardar el usuario
        return usuarioRepository.save(user);
    }
}
