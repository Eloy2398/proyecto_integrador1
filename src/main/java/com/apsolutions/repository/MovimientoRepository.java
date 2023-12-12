package com.apsolutions.repository;

import com.apsolutions.dto.MovimientoDto;
import com.apsolutions.dto.MovimientoListDto;
import com.apsolutions.dto.report.MovimientoReportDto;
import com.apsolutions.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query("SELECT new com.apsolutions.dto.MovimientoListDto(m.id, m.fecha, m.tipo, p.nombre, m.descripcion, m.estado) FROM Movimiento m LEFT JOIN m.persona p ORDER BY m.id DESC")
    List<MovimientoListDto> list();

    @Modifying
    @Query("UPDATE Movimiento m SET m.estado = :estado WHERE m.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT new com.apsolutions.dto.MovimientoDto(m.fecha, m.tipo, p.nombre, m.descripcion, m.estado, c.id) FROM Movimiento m LEFT JOIN m.persona p LEFT JOIN m.cotizacion c " +
            "WHERE m.id = :id")
    MovimientoDto read(int id);

    @Query("SELECT new com.apsolutions.dto.report.MovimientoReportDto(m.id, m.fecha, CASE WHEN m.tipo = 1 THEN 'INGRESO' ELSE 'SALIDA' END, md.cantidad, md.precio) " +
            "FROM Movimientodetalle md INNER JOIN md.movimiento m WHERE md.movimiento.fecha BETWEEN :fec1 AND :fec2 " +
            "AND md.producto.id = :idProducto")
    List<MovimientoReportDto> filter(Date fec1, Date fec2, Integer idProducto);

}
