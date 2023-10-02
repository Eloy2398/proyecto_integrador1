package com.apsolutions.repository;

import com.apsolutions.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

    @Query("SELECT c FROM Caracteristica c WHERE c.nombre = :nombre")
    Optional<Caracteristica> existsByName(String nombre);
}
