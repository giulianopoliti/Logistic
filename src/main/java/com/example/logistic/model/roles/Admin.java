package com.example.logistic.model.roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Admin extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Admin(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password, Role role) {
        super(name, lastName, dateOfBirth, tenant, email, username, password, role);
    }


    public Admin() {

    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
