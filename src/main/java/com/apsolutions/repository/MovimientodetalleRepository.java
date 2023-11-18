package com.apsolutions.repository;

import com.apsolutions.dto.MovimientodetalleDto;
import com.apsolutions.model.Movimientodetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientodetalleRepository extends JpaRepository<Movimientodetalle, Integer> {

    @Query("SELECT md FROM Movimientodetalle md WHERE md.movimiento.id = :idMovimiento")
    List<Movimientodetalle> listByIdMovimiento(Integer idMovimiento);

    @Query("SELECT new com.apsolutions.dto.MovimientodetalleDto(p.nombre, md.cantidad, md.precio) FROM Movimientodetalle md INNER JOIN md.producto p WHERE md.movimiento.id = :idMovimiento")
    List<MovimientodetalleDto> listByIdMovimientoSimplifado(Integer idMovimiento);
}
