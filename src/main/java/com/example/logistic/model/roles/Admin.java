package com.example.logistic.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
@Entity
@Getter
@Setter
@DiscriminatorValue("ADMIN")
@Table(name = "usuarios")
public class Admin extends Usuario {

    public Admin(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone, String username, String cuil, String address, Date createdAt, String profilePictureURL) {
        super(name, lastName, dateOfBirth, tenant, email, phone, emergencyPhone, username, cuil, address, createdAt, profilePictureURL);
    }

    public Admin() {

    }
    @Transient
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
