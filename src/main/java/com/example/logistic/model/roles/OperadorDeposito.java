package com.example.logistic.model.roles;

import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
@Entity
@Table(name = "usuarios")
@DiscriminatorValue("OPERADORDEPOSITO")
public class OperadorDeposito extends Usuario {
    @OneToOne
    @JoinColumn(name = "deposito_id")
    private Deposito deposito;

    public OperadorDeposito(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone, String username, String cuil, String address, Date createdAt, String profilePictureURL) {
        super(name, lastName, dateOfBirth, tenant, email, phone, emergencyPhone, username, cuil, address, createdAt, profilePictureURL);
        this.setRol(Role.OPERADORDEPOSITO);
    }

    public OperadorDeposito() {
        super();
        this.setRol(Role.OPERADORDEPOSITO);
    }
    public void asignarPedido (Pedido pedido) {
        // implementar logica
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_OPERADOR_DEPOSITO"));
    }
}
