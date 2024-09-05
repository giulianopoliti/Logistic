package com.example.logistic.repository;

import com.example.logistic.model.ruta.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    // Consulta para obtener la ruta por el id del driver
    // Consulta para obtener la ruta del driver por fecha actual
    @Query("SELECT r FROM Ruta r WHERE r.driver.id = :driverId AND r.date = :fecha AND r.completada = false")
    Ruta findByDriverId(@Param("driverId") Integer driverId, @Param("fecha") Date fecha);
}
