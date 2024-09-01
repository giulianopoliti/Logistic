package com.example.logistic.model.roles;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class Administrator extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Administrator(String name, String lastName, Date dateOfBirth, Tenant tenant, String email) {
        super(name, lastName, dateOfBirth, tenant, email);
    }

    public Administrator() {

    }


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
