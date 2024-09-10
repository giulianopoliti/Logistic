package com.example.logistic.model.roles;

import jakarta.persistence.Entity;
import java.util.Date;
@Entity
public class OperadorDeposito extends Usuario {
    public OperadorDeposito(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password) {
        super(name, lastName, dateOfBirth, tenant, email, username, password);
    }
    public OperadorDeposito() {

    }
}
