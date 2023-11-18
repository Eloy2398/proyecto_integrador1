package com.apsolutions.repository;

import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.indicator.CotizacionDto;
import com.apsolutions.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    @Query("SELECT new com.apsolutions.dto.CotizacionListDto(c.id, c.fecha, c.estado, cl.id, p.documento, p.nombre) " +
            "FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p")
    List<CotizacionListDto> list();

    @Modifying
    @Query("UPDATE Cotizacion c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT new com.apsolutions.dto.indicator.CotizacionDto(MONTHNAME(c.fecha) AS m, COUNT(c.id) AS num) FROM Cotizacion c " +
            "WHERE YEAR(c.fecha) = :anio GROUP BY MONTHNAME(c.fecha)")
    List<CotizacionDto> cotizacionesGeneradas(int anio);

    @Query("SELECT COUNT(c.id) FROM Cotizacion c")
    Integer getTotalRegistros();

    @Query("SELECT new com.apsolutions.dto.query.CotizacionDto(c.id, p.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.estado = true AND p.nombre LIKE :query")
    List<com.apsolutions.dto.query.CotizacionDto> search(String query);

    @Query("SELECT new com.apsolutions.dto.query.CotizacionDto(c.id, p.id, p.nombre) FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p " +
            "WHERE c.estado = true AND c.id = :query")
    List<com.apsolutions.dto.query.CotizacionDto> search(Integer query);
}
