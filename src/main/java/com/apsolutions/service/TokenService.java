package com.apsolutions.service;

import com.apsolutions.model.Token;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void save(Usuario usuario, String jwt) {
        Token token = new Token();
        token.setUsuario(usuario);
        token.setToken(jwt);
        token.setCaducado(false);
        token.setEliminado(false);
        tokenRepository.save(token);
    }

    public boolean isTokenAvailable(String token) {
        return tokenRepository.findByToken(token).map(jwt -> !jwt.getCaducado() && !jwt.getEliminado()).orElse(false);
    }

    public void disableToken(String headerAuthorizationToken) {
        String jwt = getTokenFromRequest(headerAuthorizationToken);
        if (jwt == null) return;

        Optional<Token> token = tokenRepository.findByToken(jwt);
        if (token.isPresent()) {
            token.get().setEliminado(true);
            token.get().setCaducado(true);
            tokenRepository.save(token.get());
        }
    }

    public String getTokenFromRequest(String headerAuthorizationToken) {
        if (headerAuthorizationToken != null && headerAuthorizationToken.startsWith("Bearer ")) {
            return headerAuthorizationToken.replace("Bearer ", "");
        }

        return null;
    }
}
