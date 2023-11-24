package com.apsolutions.repository;

import com.apsolutions.dto.website.ProductoWebsiteDto;
import com.apsolutions.model.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoWebsiteRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, p.descripcion, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1")
    List<ProductoWebsiteDto> getProductsToBanner(Pageable pageable);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.nombre, p.nombreUrl, p.precio, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1")
    List<ProductoWebsiteDto> getProductsMain(Pageable pageable);

    @Query("SELECT new com.apsolutions.dto.website.ProductoWebsiteDto(p.id, p.codigo, p.nombre, p.descripcion, c.nombre, m.nombre, p.precio, p.stock, p.imagen) FROM Producto p " +
            "INNER JOIN p.marca m INNER JOIN p.categoria c WHERE p.estado = true AND c.mostrarweb = 1 AND m.mostrarweb = 1 AND p.id = :id AND p.nombreUrl = :urlName")
    Optional<ProductoWebsiteDto> getProductData(Integer id, String urlName);
}
