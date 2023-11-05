package com.apsolutions.controller;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.model.Perfil;
import com.apsolutions.model.Usuario;
import com.apsolutions.service.PerfilService;
import com.apsolutions.service.UsuarioService;
import com.apsolutions.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<UsuarioDto>> list() {
        return usuarioService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/getAuthenticatedUser")
    public ApiResponse<UsuarioDto> getAuthenticatedUser(HttpServletRequest request) {
        return usuarioService.getAuthenticatedUser(request);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        return usuarioService.delete(id, request);
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<Usuario> read(@PathVariable("id") Integer id) {
        return usuarioService.read(id);
    }

    @PutMapping("/bloquear/{id}")
    public ApiResponse<String> bloqueo(@PathVariable("id") Integer id, HttpServletRequest request) {
        return usuarioService.bloquear(id, request);
    }

    @GetMapping("/cargarPerfil")
    public ApiResponse<List<Perfil>> listPerfil() {
        return perfilService.list();
    }

}
