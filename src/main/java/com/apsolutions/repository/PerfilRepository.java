package com.apsolutions.repository;

import com.apsolutions.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

    @Query("SELECT p FROM Perfil p WHERE p.estado = true")
    List<Perfil> list();

    @Query("SELECT p FROM Perfil p WHERE p.estado=true AND p.nombre = :nombre AND p.id != :id")
    Optional<Perfil> existsByName(String nombre, int id);

    @Modifying
    @Query("UPDATE Perfil p SET p.estado = :estado WHERE p.id = :id")
    void updateStatus(Boolean estado, Integer id);
}
