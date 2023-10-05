package com.apsolutions.service;

import com.apsolutions.security.*;
import com.apsolutions.util.ApiResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserDetailsServiceImp userDetailsServiceImp;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserDetailsServiceImp userDetailsServiceImp, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public ApiResponse<AuthResponse> login(AuthCredentials authCredentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword()));
        AuthResponse authResponse = new AuthResponse();
        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(authCredentials.getUsername());
        authResponse.setToken(jwtTokenProvider.createToken(userDetails));
        return new ApiResponse<>(true, "Usuario autenticado correctamente", authResponse);
    }
}
