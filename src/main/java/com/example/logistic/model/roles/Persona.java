package com.example.logistic.model.roles;

import java.util.Date;

public abstract class Persona {

    private String name;
    private String lastName;
    private Date dateOfBirth;
    private Tenant tenant;
    private String email;

    public Persona(String name, String lastName, Date dateOfBirth, Tenant tenant, String email) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tenant = tenant;
        this.email = email;
    }

    public Persona() {

    }
}
