package com.apsolutions.repository;

import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.model.Criterioopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CriterioopcionRepository extends JpaRepository<Criterioopcion, Integer> {

    @Query(value = "SELECT new com.apsolutions.dto.CriterioopcionDto(co.id, co.descripcion) FROM Criterioopcion co WHERE co.estado = true AND co.criterio.id = :idCriterio")
    List<CriterioopcionDto> listByIdCriterio(Integer idCriterio);

    @Query("SELECT co FROM Criterioopcion co WHERE co.criterio.id = :idCriterio AND co.descripcion = :descripcion")
    Optional<Criterioopcion> obtenerByDescripcion(Integer idCriterio, String descripcion);

    @Modifying
    @Query("UPDATE Criterioopcion co SET co.estado = :estado WHERE co.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Modifying
    @Query("UPDATE Criterioopcion co SET co.estado = :estado WHERE co.criterio.id = :idCriterio")
    void updateStatusByCriterio(Boolean estado, Integer idCriterio);
}
