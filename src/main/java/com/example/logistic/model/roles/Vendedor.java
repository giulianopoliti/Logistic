package com.example.logistic.model.roles;

import com.example.logistic.model.roles.meli.IntegracionMeliDriver;
import com.example.logistic.model.roles.meli.IntegracionMeliVendedor;
import com.example.logistic.model.ruta.paquete.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name="usuarios")
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario {
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private List<Local> locales = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendedor_id")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vendedor_id")
    private List<IntegracionMeliVendedor> integracionMeliVendedor;

    public Vendedor(String name, String lastName, Date dateOfBirth, Tenant tenant, String email, String phone, String emergencyPhone, String username, String cuil, String address, Date createdAt, String profilePictureURL) {
        super(name, lastName, dateOfBirth, tenant, email, phone, emergencyPhone, username, cuil, address, createdAt, profilePictureURL);
        this.pedidos = new ArrayList<>();
        this.locales = new ArrayList<>();
        this.integracionMeliVendedor = new ArrayList<>();
        this.setRol(Role.VENDEDOR);
    }

    public Vendedor() {
        super();
        this.setRol(Role.VENDEDOR);
    }


    public void addLocal(Local local) {
        this.locales.add(local);
    }
    public void removeLocal(Local local) {
        this.locales.remove(local);
    }

    public void removePaquete(Pedido pedido) {
        this.pedidos.remove(pedido);
    }

    public void cargarPaquetesExcel() {
        /// implementar logica
    }
    public boolean isIntegrateMeli () {
        return !this.integracionMeliVendedor.isEmpty();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
    }
}
