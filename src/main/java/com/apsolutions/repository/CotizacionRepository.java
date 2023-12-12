package com.apsolutions.repository;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.indicator.CotizacionIndicatorDto;
import com.apsolutions.dto.query.CotizacionQueryDto;
import com.apsolutions.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    @Query("SELECT new com.apsolutions.dto.CotizacionListDto(c.id, c.fecha, c.estado, cl.id, p.documento, p.nombre) " +
            "FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p WHERE c.origen = 1 ORDER BY c.id DESC")
    List<CotizacionListDto> list();

    @Modifying
    @Query("UPDATE Cotizacion c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Byte estado, Integer id);

    @Query("SELECT new com.apsolutions.dto.CotizacionDto(c.fecha, cl.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.id = :id")
    CotizacionDto read(int id);

    @Query("SELECT new com.apsolutions.dto.indicator.CotizacionIndicatorDto(MONTHNAME(c.fecha) AS m, COUNT(c.id) AS num) FROM Cotizacion c " +
            "WHERE YEAR(c.fecha) = :anio GROUP BY MONTHNAME(c.fecha)")
    List<CotizacionIndicatorDto> cotizacionesGeneradas(int anio);

    @Query("SELECT COUNT(c.id) FROM Cotizacion c")
    Integer getTotalRegistros();

    @Query("SELECT new com.apsolutions.dto.query.CotizacionQueryDto(c.id, p.id, p.nombre) FROM Cotizacion c LEFT JOIN c.cliente cl LEFT JOIN cl.persona p " +
            "WHERE c.estado = 1 AND p.nombre LIKE :query")
    List<CotizacionQueryDto> searchByName(String query);

    @Query("SELECT new com.apsolutions.dto.query.CotizacionQueryDto(c.id, p.id, p.nombre) FROM Cotizacion c LEFT JOIN c.cliente cl LEFT JOIN cl.persona p " +
            "WHERE c.estado = 1 AND c.id = :query")
    List<CotizacionQueryDto> searchById(Integer query);
}
