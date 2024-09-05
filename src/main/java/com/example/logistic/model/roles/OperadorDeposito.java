package com.example.logistic.model.roles;

import com.example.logistic.model.paquete.Paquete;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class OperadorDeposito extends Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public OperadorDeposito(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String password, Role role) {
        super(name, lastName, dateOfBirth, tenant, email, username, password, role);
    }

    public OperadorDeposito() {

    }
}
