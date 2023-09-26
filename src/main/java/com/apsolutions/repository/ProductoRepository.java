package com.apsolutions.repository;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT new com.apsolutions.dto.ProductoDto(p.id, p.codigo, p.nombre, p.descripcion, c.nombre, m.nombre) " +
            "FROM Producto p INNER JOIN p.categoria c INNER JOIN p.marca m")
    List<ProductoDto> list();

    @Query(value = "SELECT p.id, p.codigo, p.nombre, p.descripcion, c.nombre AS categoriaNombre, m.nombre AS marcaNombre " +
            "FROM producto p INNER JOIN categoria c ON p.idcategoria = c.id INNER JOIN marca m ON p.idmarca = m.id", nativeQuery = true)
    List<ProductoDto> listCs();
}
