package com.apsolutions.repository;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //@Query(value = "SELECT u.id, u.nombre, u.usuario, p.nombre FROM Usuario u INNER JOIN Perfil p ON u.idperfil = p.id", nativeQuery = true)
    @Query("SELECT new com.apsolutions.dto.UsuarioDto(u.id, u.nombre, u.usuario, u.bloqueado, p.nombre, p.id) FROM Usuario u INNER JOIN u.perfil p WHERE u.estado = true")
    List<UsuarioDto> list();

    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.usuario = :usuario AND u.id <> :id")
    Optional<Usuario> existsByUsername(String usuario, Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.id = :id")
    Optional<Usuario> searchById(Integer id);

    @Modifying
    @Query("UPDATE Usuario u SET u.estado = :estado WHERE u.id = :id")
    void updateStatus(Boolean estado, Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.estado = true AND u.usuario LIKE :usuario")
    Optional<Usuario> findByUsername(String usuario);

    @Modifying
    @Query("UPDATE Usuario u SET u.bloqueado = :bloqueo WHERE u.id = :id")
    void updateBloqueo(Integer id, Byte bloqueo);
}
