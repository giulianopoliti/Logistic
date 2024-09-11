package com.example.logistic.model.roles.meli;

import com.example.logistic.model.roles.Driver;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("DRIVER")
public class IntegracionMeliDriver extends IntegracionMeli {
    @Column(unique = true)
    private String meliFlexUserId;
    @OneToOne(mappedBy = "integracionMeliDriver")
    private Driver driver;
}
