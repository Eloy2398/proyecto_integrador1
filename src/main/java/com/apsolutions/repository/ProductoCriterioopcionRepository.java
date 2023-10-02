package com.apsolutions.repository;

import com.apsolutions.model.ProductoCriterioopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductoCriterioopcionRepository extends JpaRepository<ProductoCriterioopcion, Integer> {

    @Modifying
    @Query("UPDATE ProductoCriterioopcion pc SET pc.estado = :estado WHERE pc.producto.id = :idProducto")
    void updateStatusByIdProducto(Boolean estado, Integer idProducto);

    @Modifying
    @Query("UPDATE ProductoCriterioopcion pc SET pc.estado = :estado WHERE pc.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT pc FROM ProductoCriterioopcion pc WHERE pc.producto.id = :idProducto AND pc.criterioopcion.id = :idCriterioopcion")
    Optional<ProductoCriterioopcion> existsByIdProductoAndIdCriterioopcion(Integer idProducto, Integer idCriterioopcion);
}
