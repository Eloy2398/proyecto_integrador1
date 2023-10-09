package com.apsolutions.repository;

import com.apsolutions.model.Cliente;
import com.apsolutions.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT p.nombre, p.documento, p.tipodocumento,  p.direccion, p.email, p.telefono, c.id " +
            "FROM Persona p INNER JOIN Cliente c ON p.id = c.idpersona WHERE c.estado = true AND c.idpersona = p.id", nativeQuery = true)
    List<Cliente> list();
}
