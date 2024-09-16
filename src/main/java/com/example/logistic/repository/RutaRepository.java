package com.example.logistic.repository;

import com.example.logistic.model.ruta.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    // Consulta para obtener la ruta por el id del driver
    // Consulta para obtener la ruta del driver por fecha actual
    @Query("SELECT r FROM Ruta r WHERE r.driver.authId = :driverId AND r.date = :date AND r.completada = false")
    Ruta findByDriverId(@Param("driverId") UUID driverId, @Param("date") Date date);

    @Query("SELECT r FROM Ruta r WHERE r.date = :date and r.tenant.id = :tenantId")
    List<Ruta> findByDay(@Param("date") Date date ,@Param("tenantId") Long tenantId);
}
