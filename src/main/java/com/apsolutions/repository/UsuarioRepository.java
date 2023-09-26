package com.apsolutions.repository;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT u.id, u.nombre, u.usuario, p.nombre FROM Usuario u INNER JOIN Perfil p ON u.idperfil = p.id", nativeQuery = true)
    List<UsuarioDto> list();
}
