package com.apsolutions.controller;

import com.apsolutions.model.Perfil;
import com.apsolutions.service.PerfilService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/perfil")
public class PerfilController {
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<Perfil>> list() {
        return perfilService.list();
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<Perfil> list(@PathVariable("id") Integer id) {
        return perfilService.read(id);
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return perfilService.delete(id);
    }
}
