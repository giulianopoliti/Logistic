package com.example.logistic.service;

import com.example.logistic.model.roles.Admin;
import com.example.logistic.model.roles.Driver;
import com.example.logistic.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminService extends UsuarioService<Admin> {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void save (Admin admin) {
        if (admin == null) {
            throw new RuntimeException("Error al guardar el admin");
        }
        adminRepository.save(admin);
    }
    public Admin createAdmin(Map<String, Object> adminData) throws InstantiationException, IllegalAccessException {
        return createUser(adminData, Admin.class);
    }
}
