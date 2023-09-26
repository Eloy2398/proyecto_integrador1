package com.apsolutions.controller;

import com.apsolutions.model.Perfil;
import com.apsolutions.service.PerfilService;
import com.apsolutions.util.ApiResponse;
import jdk.jfr.Frequency;
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

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody Perfil perfil) {
        return perfilService.edit(id, perfil);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return perfilService.delete(id);
    }
}
