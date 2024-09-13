package com.example.logistic.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Getter
@Setter
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {


    public Admin(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }

    public Admin() {

    }



}
