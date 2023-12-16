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

    @Query("SELECT new com.apsolutions.dto.ProductoCaracteristicaDto(c.id, pc.valor) FROM ProductoCaracteristica pc INNER JOIN pc.caracteristica c WHERE pc.producto.id = :idProduct")
    List<ProductoCaracteristicaDto> getByIdProduct(Integer idProduct);

    @Query("SELECT new com.apsolutions.dto.ProductoCaracteristicaDto(c.id, c.nombre) FROM ProductoCaracteristica pc INNER JOIN pc.caracteristica c WHERE pc.producto.id IN (:idProducts) GROUP BY c.id")
    List<ProductoCaracteristicaDto> getOnlyCharacteristicsByIdProducts(List<Integer> idProducts);
}
