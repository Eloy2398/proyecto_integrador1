package com.apsolutions.repository;

import com.apsolutions.dto.PersonaBusquedaDto;
import com.apsolutions.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    @Query("SELECT p FROM Persona p WHERE p.documento = :documento AND p.id <> :id")
    Optional<Persona> existsByDocument(String documento, Integer id);

    @Query("SELECT new com.apsolutions.dto.PersonaBusquedaDto(p.id, p.documento, p.nombre) FROM Persona p WHERE p.nombre LIKE :query")
    List<PersonaBusquedaDto> search(String query);
}
