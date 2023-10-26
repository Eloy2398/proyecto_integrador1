package com.apsolutions.controller;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.model.Perfil;
import com.apsolutions.model.Usuario;
import com.apsolutions.service.PerfilService;
import com.apsolutions.service.UsuarioService;
import com.apsolutions.util.ApiResponse;
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
    public ApiResponse<List<UsuarioDto>> list(){
        return usuarioService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Usuario usuario){
        return usuarioService.save(usuario);
    }

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody Usuario usuario){
        return usuarioService.edit(id, usuario);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id){
        return usuarioService.delete(id);
    }

    @GetMapping("cargarPerfil")
    public ApiResponse<List<Perfil>> listPerfil() {
        return perfilService.list();
    }
}
