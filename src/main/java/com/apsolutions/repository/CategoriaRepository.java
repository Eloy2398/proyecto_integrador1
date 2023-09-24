package com.apsolutions.repository;

import com.apsolutions.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query(value = "SELECT c FROM Categoria c WHERE c.estado = true")
    List<Categoria> listOnlyActive();

}