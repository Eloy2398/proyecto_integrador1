package com.apsolutions.repository;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.indicator.CotizacionIndicatorDto;
import com.apsolutions.dto.query.CotizacionQueryDto;
import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    @Query("SELECT new com.apsolutions.dto.CotizacionListDto(c.id, c.fecha, c.estado, cl.id, p.documento, p.nombre) " +
            "FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p")
    List<CotizacionListDto> list();

    @Modifying
    @Query("UPDATE Cotizacion c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT new com.apsolutions.dto.CotizacionQueryDto(c.fecha, cl.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.id = :id")
    CotizacionDto read(int id);

    @Query("SELECT new com.apsolutions.dto.indicator.CotizacionQueryDto(MONTHNAME(c.fecha) AS m, COUNT(c.id) AS num) FROM Cotizacion c " +
            "WHERE YEAR(c.fecha) = :anio GROUP BY MONTHNAME(c.fecha)")
    List<CotizacionIndicatorDto> cotizacionesGeneradas(int anio);

    @Query("SELECT COUNT(c.id) FROM Cotizacion c")
    Integer getTotalRegistros();

    @Query("SELECT new com.apsolutions.dto.query.CotizacionQueryDto(c.id, p.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.estado = 1 AND p.nombre LIKE :query")
    List<CotizacionQueryDto> search(String query);

    @Query("SELECT new com.apsolutions.dto.query.CotizacionQueryDto(c.id, p.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.estado = 1 AND c.id = :query")
    List<CotizacionQueryDto> search(Integer query);

    @Query("SELECT new com.apsolutions.dto.report.CotizacionReportDto(c.id, c.fecha, p.documento, p.nombre, c.estado, c.origen) " +
            "FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p WHERE c.fecha BETWEEN :fec1 AND :fec2 " +
            "AND (:idCliente IS NULL OR c.cliente.id = :idCliente)")
    List<CotizacionReportDto> filter(Date fec1, Date fec2, Integer idCliente);
}
