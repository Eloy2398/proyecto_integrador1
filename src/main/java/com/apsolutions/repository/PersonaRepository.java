package com.apsolutions.repository;

import com.apsolutions.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.estado = true AND c.persona.documento = :documento AND c.id <> :id")
    Optional<Persona> existsByDocument(String documento, Integer id);
}
