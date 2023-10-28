package com.apsolutions.repository;

import com.apsolutions.model.Token;
import com.apsolutions.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t FROM Token t WHERE t.token = :token")
    Optional<Token> findByToken(String token);

    @Query("SELECT t.usuario FROM Token t WHERE t.token = :token")
    Optional<Usuario> getUserByToken(String token);
}
