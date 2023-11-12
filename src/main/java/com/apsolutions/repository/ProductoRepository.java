package com.apsolutions.repository;

import com.apsolutions.dto.IndicadorProductoDto;
import com.apsolutions.dto.ProductoBusquedaDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.dto.ProductoWebsiteDto;
import com.apsolutions.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT p.imagen FROM Producto p WHERE p.id = :id")
    String getImage(Integer id);

    @Modifying
    @Query("UPDATE Producto p SET p.stock = :stock WHERE p.id = :id")
    void updateStock(Integer stock, Integer id);

    @Modifying
    @Query("UPDATE Producto p SET p.estado = :estado WHERE p.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT new com.apsolutions.dto.IndicadorProductoDto(p.nombre, p.stock, SUM(CASE WHEN m.tipo = 1 THEN md.cantidad ELSE 0 END) AS inc, SUM(CASE WHEN m.tipo = 2 THEN md.cantidad ELSE 0 END) AS out) FROM Movimientodetalle md " +
            "LEFT JOIN md.movimiento m LEFT JOIN md.producto p WHERE m.estado = true AND p.estado = true AND YEAR(m.fecha) = :anio GROUP BY p.id ORDER BY p.stock ASC LIMIT 10")
    List<IndicadorProductoDto> stockproductos(int anio);

    @Query("SELECT COUNT(p.id) FROM Producto p WHERE p.estado = true")
    Integer getTotalRegistros();

    @Query("SELECT new com.apsolutions.dto.ProductoWebsiteDto(p.id, p.nombre, p.descripcion, p.imagen) FROM Producto p WHERE p.estado = true")
    List<ProductoWebsiteDto> getProductsToBanner(Pageable pageable);
}
