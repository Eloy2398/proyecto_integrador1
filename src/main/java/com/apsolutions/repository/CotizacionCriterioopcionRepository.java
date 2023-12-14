package com.apsolutions.repository;

import com.apsolutions.dto.CotizacionCriterioOpcionDto;
import com.apsolutions.dto.CotizaciondetalleDto;
import com.apsolutions.model.CotizacionCriterioopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizacionCriterioopcionRepository extends JpaRepository<CotizacionCriterioopcion, Integer> {
    @Query("SELECT new com.apsolutions.dto.CotizacionCriterioOpcionDto(co.descripcion, cr.nombre) FROM CotizacionCriterioopcion cc INNER JOIN cc.criterioopcion co INNER JOIN co.criterio cr WHERE cc.cotizacion.id = :idCotizacion")
    List<CotizacionCriterioOpcionDto> listByIdCotizacionCriterio(Integer idCotizacion);
}
