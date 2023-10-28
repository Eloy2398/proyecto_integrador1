package com.apsolutions.controller;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Perfil;
import com.apsolutions.model.Usuario;
import com.apsolutions.service.PerfilService;
import com.apsolutions.service.UsuarioService;
import com.apsolutions.util.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
        return usuarioService.edit(id, usuario);
    }

    @GetMapping("/getAuthenticatedUser")
    public ApiResponse<UsuarioDto> getAuthenticatedUser(HttpServletRequest request) {
        return usuarioService.getAuthenticatedUser(request);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id, @RequestBody Map<String, Object> requestBody) {
        String username = requestBody.get("username").toString();
        Integer perfil_id = Integer.parseInt(requestBody.get("perfil_id").toString());
        return usuarioService.delete(id, perfil_id, username);
    }

    @PutMapping("/bloquear/{id}")
    public ApiResponse<String> bloqueo(@PathVariable("id") Integer id, @RequestBody Map<String, Object> requestBody) {
        Byte bloqueo = null;
        String username = null;

        if (requestBody.containsKey("bloqueo")) {
            try {
                bloqueo = Byte.parseByte(requestBody.get("bloqueo").toString());
            } catch (NumberFormatException e) {
                throw new CsException("Error: "+e.getMessage());
            }
        }

        if (requestBody.containsKey("username")) {
            username = requestBody.get("username").toString();
        }
        return usuarioService.bloquear(id, bloqueo, username);
    }

    @GetMapping("/cargarPerfil")
    public ApiResponse<List<Perfil>> listPerfil() {
        return perfilService.list();
    }

}
