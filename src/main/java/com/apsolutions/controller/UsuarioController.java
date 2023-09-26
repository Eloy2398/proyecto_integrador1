package com.apsolutions.controller;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.service.UsuarioService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<UsuarioDto>> list(){
        return usuarioService.list();
    }
}
