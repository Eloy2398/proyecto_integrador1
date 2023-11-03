package com.apsolutions.repository;

import com.apsolutions.dto.MovimientoListDto;
import com.apsolutions.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query("SELECT new com.apsolutions.dto.MovimientoListDto(m.id, m.fecha, m.tipo, p.nombre, m.descripcion, m.estado) FROM Movimiento m INNER JOIN m.persona p")
    List<MovimientoListDto> list();

    @Modifying
    @Query("UPDATE Movimiento m SET m.estado = :estado WHERE m.id = :id")
    void updateStatus(Boolean estado, Integer id);
}
