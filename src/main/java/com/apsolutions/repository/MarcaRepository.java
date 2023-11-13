package com.apsolutions.repository;

import com.apsolutions.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

    @Query("SELECT m FROM Marca m WHERE m.estado = true AND m.nombre = :nombre AND m.id <> :id")
    Optional<Marca> existsByName(String nombre, Integer id);

    @Query("SELECT m FROM Marca m WHERE m.estado = true")
    List<Marca> list();

    @Modifying
    @Query("UPDATE Marca m SET m.estado = :estado WHERE m.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT m.imagen FROM Marca m WHERE m.id = :id")
    String getImage(Integer id);

}
