package com.apsolutions.service;

import com.apsolutions.security.*;
import com.apsolutions.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;

    public AuthService(UserDetailsServiceImp userDetailsServiceImp, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, TokenService tokenService) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;
    }

    public ApiResponse<AuthResponse> login(AuthCredentials authCredentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword()));
        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtTokenProvider.createToken(userDetailsServiceImp.loadUserByUsername(authCredentials.getUsername())));
        tokenService.save(userDetailsServiceImp.getUserEntity(), authResponse.getToken());

        return new ApiResponse<>(true, "Usuario autenticado correctamente", authResponse);
    }

    public ApiResponse<String> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        tokenService.disableToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, authentication);
        SecurityContextHolder.clearContext();
        return new ApiResponse<>(true, "Sesión finalizada");
    }
}
