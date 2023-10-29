package com.apsolutions.repository;

import com.apsolutions.dto.ProductoBusquedaDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT new com.apsolutions.dto.ProductoListDto(p.id, p.codigo, p.nombre, p.descripcion, c.nombre, m.nombre, p.precio, p.stock) FROM Producto p INNER JOIN p.categoria c INNER JOIN p.marca m WHERE p.estado = true")
    List<ProductoListDto> list();

    @Query("SELECT new com.apsolutions.dto.ProductoBusquedaDto(p.id, p.nombre, p.precio) FROM Producto p WHERE p.estado = true AND p.nombre LIKE :query")
    List<ProductoBusquedaDto> search(String query);

    @Query(value = "SELECT p.id, p.codigo, p.nombre, p.descripcion, c.nombre AS categoriaNombre, m.nombre AS marcaNombre " +
            "FROM producto p INNER JOIN categoria c ON p.idcategoria = c.id INNER JOIN marca m ON p.idmarca = m.id", nativeQuery = true)
    List<ProductoListDto> listCs();

    @Query("SELECT p FROM Producto p WHERE p.estado = true AND p.nombre LIKE :nombre AND p.marca.id = :idmarca AND p.id <> :id")
    Optional<Producto> existsByNameAndBrand(String nombre, Integer idmarca, Integer id);

    @Query("SELECT p FROM Producto p WHERE p.estado = true AND p.codigo LIKE :codigo AND p.id <> :id")
    Optional<Producto> existsByCode(String codigo, Integer id);

    @Query("SELECT p.stock FROM Producto p WHERE p.id = :id")
    Integer getStock(Integer id);

    @Modifying
    @Query("UPDATE Producto p SET p.stock = :stock WHERE p.id = :id")
    void updateStock(Integer stock, Integer id);

    @Modifying
    @Query("UPDATE Producto p SET p.estado = :estado WHERE p.id = :id")
    void updateStatus(Boolean estado, Integer id);
}
