package com.apsolutions.repository;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.model.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
    @Query("SELECT new com.apsolutions.dto.CotizacionDto(c.id, c.fecha, c.estado, cl.id, p.documento, p.nombre) " +
            "FROM Cotizacion c INNER JOIN c.cliente cl INNER JOIN cl.persona p")
    List<CotizacionDto> list();
}
