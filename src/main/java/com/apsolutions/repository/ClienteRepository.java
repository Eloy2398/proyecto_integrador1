package com.apsolutions.repository;

import com.apsolutions.dto.ClienteDto;
import com.apsolutions.model.Cliente;
import com.apsolutions.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c INNER JOIN c.persona p " +
            "WHERE p.documento = :documento AND c.id <> :id")
    Optional<Cliente> existsByDocument(String documento, Integer id);

    @Query("SELECT new com.apsolutions.dto.ClienteDto(p.id, p.nombre, p.documento, p.tipodocumento, p.telefono, p.direccion, p.email, c.id, c.estado) " +
            "FROM Cliente c INNER JOIN c.persona p")
    List<ClienteDto> list();

    @Modifying
    @Query("UPDATE Cliente c SET c.estado = :estado WHERE c.id = :id")
    void updateStatus(Boolean estado, Integer id);
}
