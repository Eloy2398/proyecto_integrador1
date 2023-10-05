package com.apsolutions.controller;

import com.apsolutions.security.AuthCredentials;
import com.apsolutions.security.AuthResponse;
import com.apsolutions.service.AuthService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody AuthCredentials authCredentials) {
        return authService.login(authCredentials);
    }
}
