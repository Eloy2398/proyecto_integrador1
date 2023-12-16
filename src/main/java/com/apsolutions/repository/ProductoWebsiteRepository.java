package com.apsolutions.repository;

import com.apsolutions.dto.website.ProductoWebsiteDto;
import com.apsolutions.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoWebsiteRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, p.descripcion, p.imagen, p.precio) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1")
    List<ProductoWebsiteDto> getProductsToBanner(Pageable pageable);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, m.nombre, p.precio, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1")
    List<ProductoWebsiteDto> getProductsMain(Pageable pageable);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.codigo, p.nombre, p.descripcion, c.nombre, m.nombre, p.precio, p.stock, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND p.id = :id AND p.nombreUrl = :urlName")
    Optional<ProductoWebsiteDto> getProductData(Integer id, String urlName);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, m.nombre, p.precio, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND c.id = :idCategory AND p.id <> :idProduct")
    List<ProductoWebsiteDto> getSimilarProducts(int idCategory, int idProduct, Pageable pageable);

    @Query("SELECT p.categoria.id FROM Producto p WHERE p.id = :id")
    Integer getIdCategoryByIdProduct(int id);

    @Query("SELECT p.precio FROM Producto p WHERE p.id = :id")
    BigDecimal getPrice(int id);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, m.nombre, p.precio, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND p.id IN (:idProducts)")
    List<ProductoWebsiteDto> getProductsCompare(List<Integer> idProducts);
}
