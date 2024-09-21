package com.example.logistic.model.roles;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("SUPERADMIN")
public class SuperAdmin extends Usuario {
    public SuperAdmin(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone, String username, String cuil, String address, String profilePictureURL) {
        super(name, lastName, dateOfBirth, tenant, email, phone, emergencyPhone, username, cuil, address, profilePictureURL);
    }

    public SuperAdmin() {
        super();
    }
}
