package com.apsolutions.repository;

import com.apsolutions.model.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CriterioRepository extends JpaRepository<Criterio, Integer> {
    @Query("SELECT c FROM Criterio c WHERE c.estado = true AND c.nombre = :nombre AND c.id <> :id")
    Optional<Criterio> existsByName(String nombre, Integer id);

    @Query("SELECT c FROM Criterio c WHERE c.estado = true")
    List<Criterio> list();
}
