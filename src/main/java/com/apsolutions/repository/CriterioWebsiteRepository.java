package com.apsolutions.repository;

import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.dto.website.CriterioWebsiteDto;
import com.apsolutions.model.Criterio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterioWebsiteRepository extends JpaRepository<Criterio, Integer> {

    @Query("SELECT new com.apsolutions.dto.website.CriterioWebsiteDto(c.id, c.nombre) FROM Criterio c WHERE c.estado = true ORDER BY c.ordenMostrar ASC")
    List<CriterioWebsiteDto> getAll();

    @Query("SELECT new com.apsolutions.dto.CriterioopcionDto(co.id, co.descripcion) FROM Criterioopcion co WHERE co.estado = true AND co.criterio.id = :idCriterio")
    List<CriterioopcionDto> listByIdCriteria(Integer idCriterio);
}
