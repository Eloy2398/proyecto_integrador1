package com.apsolutions.repository;

import com.apsolutions.dto.ClienteDto;
import com.apsolutions.dto.query.PersonaQueryDto;
import com.apsolutions.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c INNER JOIN c.persona p " +
            "WHERE p.documento = :documento AND p.id <> :id")
    Optional<Cliente> existsByDocument(String documento, Integer id);

    @Query("SELECT new com.apsolutions.dto.ClienteDto(p.id, p.nombre, p.documento, " +
            "CASE WHEN p.tipodocumento = 1 THEN 'DNI' ELSE 'RUC' END, " +
            "p.telefono, p.direccion, p.email, c.id, c.estado) " +
            "FROM Cliente c INNER JOIN c.persona p WHERE c.estado = true")
    List<ClienteDto> list();

    @Query("SELECT c FROM Cliente c INNER JOIN c.persona p WHERE p.id = :id")
    Optional<Cliente> obtenerById(Integer id);

    @Query("SELECT p.nombre FROM Cliente c INNER JOIN c.persona p WHERE c.id = :id")
    String getName(Integer id);

    @Modifying
    @Query("UPDATE Cliente c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT COUNT(c.id) FROM Cliente c WHERE c.estado = true")
    Integer getTotalRegistros();

    @Query("SELECT new com.apsolutions.dto.query.PersonaQueryDto(c.id, p.documento, p.nombre) FROM Cliente c INNER JOIN c.persona p WHERE c.id > 1 AND p.nombre LIKE :query")
    List<PersonaQueryDto> search(String query);
}
