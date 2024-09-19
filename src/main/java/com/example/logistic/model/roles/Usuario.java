package com.example.logistic.model.roles;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.validation.constraints.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {

    @Id
    @Column(name = "auth_id")
    private UUID authId;

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @Past
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$")
    private String phone;

    @Pattern(regexp = "^\\+?[0-9]{10,14}$")
    private String emergencyPhone;


    @NotBlank
    @Column(unique = true)
    private String username;


    private boolean active;

    @Pattern(regexp = "^\\d{2}-\\d{8}-\\d{1}$")
    private String cuil;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String profilePictureURL;
    @OneToOne
    @JoinColumn(name = "auth_id")
    private Configuration configuration;

    @Enumerated(EnumType.STRING)
    private Role rol;


    public Usuario(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone,
                   String username, String cuil, String address, Date createdAt, String profilePictureURL) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.tenant = tenant;
        this.email = email;
        this.phone = phone;
        this.emergencyPhone = emergencyPhone;
        this.username = username;
        this.active = true;
        this.cuil = cuil;
        this.address = address;
        this.profilePictureURL = profilePictureURL;
    }

    public Usuario() {

    }
    // Método para hashear la contraseña
    @PrePersist
    protected void onCreate () {
        createdAt = new Date();
    }


    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.username.equals("superadmin")) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPERADMIN"));
        }
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
