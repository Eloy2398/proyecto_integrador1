package com.apsolutions.repository;

import com.apsolutions.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Query("UPDATE Usuario u SET u.usuario = :username WHERE u.id = :id")
    void updateUsername(String username, Integer id);

    @Modifying
    @Query("UPDATE Usuario u SET u.clave = :password WHERE u.id = :id")
    void updatePassword(String password, Integer id);
}
