package com.apsolutions.repository;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.model.Criterio;
import com.apsolutions.model.Criterioopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CriterioopcionRepository extends JpaRepository<Criterioopcion, Integer> {

    @Query(value = "SELECT co FROM Criterioopcion co WHERE co.criterio.id=:idCriterio")
    List<Criterioopcion> listCriterioOpcionById(Integer idCriterio);
}
