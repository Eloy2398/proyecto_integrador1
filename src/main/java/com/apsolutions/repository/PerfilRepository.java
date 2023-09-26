package com.apsolutions.repository;

import com.apsolutions.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    @Query("SELECT p FROM Perfil p WHERE p.estado = true")
    public List<Perfil> list();

    @Query("SELECT p FROM Perfil p WHERE p.estado=true AND p.nombre = :nombre AND p.id != :id")
    public Optional<Perfil> existsByName(String nombre, int id);
}
