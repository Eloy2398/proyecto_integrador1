package com.apsolutions.controller;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public List<CategoriaDto> listOnlyActive() {
        return categoriaService.listOnlyActive();
    }

    @GetMapping("/listarTodo")
    public List<CategoriaDto> list() {
        return categoriaService.listAll();
    }

    @PostMapping("/guardar")
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }
}
