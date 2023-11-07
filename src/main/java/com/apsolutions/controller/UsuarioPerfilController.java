package com.apsolutions.controller;

import com.apsolutions.dto.UsuarioPerfilDto;
import com.apsolutions.service.UsuarioPerfilService;
import com.apsolutions.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuarioperfil")
public class UsuarioPerfilController {

    private final UsuarioPerfilService usuarioPerfilService;

    public UsuarioPerfilController(UsuarioPerfilService usuarioPerfilService) {
        this.usuarioPerfilService = usuarioPerfilService;
    }

    @PostMapping("/update")
    public ApiResponse<String> save(@RequestBody UsuarioPerfilDto usuarioPerfilDto, HttpServletRequest request) {
        return usuarioPerfilService.updateUsernamePassword(usuarioPerfilDto, request);
    }
}
