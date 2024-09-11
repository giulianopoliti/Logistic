package com.example.logistic.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean active;
    private String domain;
    private String email;
    @OneToMany(mappedBy = "tenant")
    private List<Admin> admins;
    private String logoURL;
}
