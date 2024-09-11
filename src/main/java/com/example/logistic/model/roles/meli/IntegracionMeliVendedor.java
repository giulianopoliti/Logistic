package com.example.logistic.model.roles.meli;

import com.example.logistic.model.roles.Vendedor;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VENDEDOR")
public class IntegracionMeliVendedor extends IntegracionMeli {
    @Column(unique = true)
    private String meliUserId;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;
    // Getters y setters
}
