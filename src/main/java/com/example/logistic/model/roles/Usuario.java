package com.example.logistic.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
    private String email;
    private String phone;
    private String username;
    @Column(name = "password_hash")
    private String passwordHash; // Renombrado para reflejar que almacena el hash de la contraseña
    private boolean active;

    public Usuario(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String username, String passwordHash) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tenant = tenant;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.active = true;
    }

    public Usuario() {

    }
    // Método para hashear la contraseña
    public void setPassword(String password) {
        this.passwordHash = hashPassword(password);
    }
    // Método para hashear una contraseña usando BCrypt
    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
