package com.example.logistic.repository;

import com.example.logistic.mapper.ViajeMapper;
import com.example.logistic.model.paquete.Paquete;
import com.example.logistic.model.ruta.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {
    @Query("SELECT v FROM Viaje v WHERE v.driver.id = :driverId")
    List<Viaje> findViajesByDriverId(Integer driverId);

    @Query("SELECT v FROM Viaje v WHERE v.driver.id = :driverId and v.fechaCreacion= :date")
    List<Viaje> findViajesByDriverIdHoy(Integer driverId, Date date);
}
