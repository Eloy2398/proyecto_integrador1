package com.apsolutions.repository;

import com.apsolutions.model.Movimientodetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientodetalleRepository extends JpaRepository<Movimientodetalle, Integer> {

    @Query("SELECT m FROM Movimientodetalle m WHERE m.movimiento.id = :idMovimiento")
    List<Movimientodetalle> listByIdMovimiento(Integer idMovimiento);
}
