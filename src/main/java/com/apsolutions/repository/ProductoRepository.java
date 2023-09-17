package com.apsolutions.repository;

import com.apsolutions.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT p.id, p.codigo, p.nombre, p.descripcion, p.categoria.nombre AS categoria, p.marca.nombre AS marca FROM producto p")
    List<Producto> listar();
}
