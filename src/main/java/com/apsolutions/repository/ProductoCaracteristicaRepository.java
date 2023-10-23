package com.apsolutions.repository;

import com.apsolutions.dto.ProductoCaracteristicaDto;
import com.apsolutions.model.ProductoCaracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoCaracteristicaRepository extends JpaRepository<ProductoCaracteristica, Integer> {
    @Modifying
    @Query("DELETE FROM ProductoCaracteristica pc WHERE pc.producto.id = :idProducto")
    void deleteByIdProducto(Integer idProducto);

    @Query("SELECT new com.apsolutions.dto.ProductoCaracteristicaDto(c.nombre, pc.valor) FROM ProductoCaracteristica pc INNER JOIN pc.caracteristica c WHERE pc.producto.id = :idProducto")
    List<ProductoCaracteristicaDto> findByIdProducto(Integer idProducto);
}
