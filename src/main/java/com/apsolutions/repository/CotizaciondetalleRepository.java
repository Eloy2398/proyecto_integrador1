package com.apsolutions.repository;

import com.apsolutions.dto.CotizaciondetalleDto;
import com.apsolutions.model.Cotizaciondetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CotizaciondetalleRepository extends JpaRepository<Cotizaciondetalle, Integer> {

    @Query("SELECT c FROM Cotizaciondetalle c WHERE c.cotizacion.id = :idCotizacion")
    List<Cotizaciondetalle> listByIdCotizacion(Integer idCotizacion);

    @Query("SELECT new com.apsolutions.dto.CotizaciondetalleDto(p.id, p.nombre, cd.cantidad, cd.precio) FROM Cotizaciondetalle cd INNER JOIN cd.producto p WHERE cd.cotizacion.id = :idCotizacion")
    List<CotizaciondetalleDto> listByIdCotizacionSimplifado(Integer idCotizacion);
}
