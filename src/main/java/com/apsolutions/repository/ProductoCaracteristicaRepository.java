package com.apsolutions.repository;

import com.apsolutions.model.ProductoCaracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductoCaracteristicaRepository extends JpaRepository<ProductoCaracteristica, Integer> {
    @Modifying
    @Query("DELETE FROM ProductoCaracteristica pc WHERE pc.producto.id = :idProducto")
    void deleteByIdProducto(Integer idProducto);
}
