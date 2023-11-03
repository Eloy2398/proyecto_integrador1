package com.apsolutions.repository;

import com.apsolutions.dto.ProveedorDto;
import com.apsolutions.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    @Query("SELECT new com.apsolutions.dto.ProveedorDto(p.id, p.nombre, p.documento, " +
            "CASE WHEN p.tipodocumento = 1 THEN 'DNI' ELSE 'RUC' END, " +
            "p.telefono, p.direccion, p.email, pv.id, pv.estado) " +
            "FROM Proveedor pv INNER JOIN pv.persona p WHERE pv.estado = true")
    List<ProveedorDto> list();

    @Query("SELECT pv FROM Proveedor pv INNER JOIN pv.persona p " +
            "WHERE p.documento = :documento AND pv.id <> :id")
    Optional<Proveedor> existsByDocument(String documento, Integer id);

    @Query("SELECT pv FROM Proveedor pv INNER JOIN pv.persona p WHERE p.id = :id")
    Optional<Proveedor> obtenerById(Integer id);

    @Modifying
    @Query("UPDATE Proveedor p SET p.estado = :estado WHERE p.id = :id")
    void updateStatus(Boolean estado, Integer id);
}
