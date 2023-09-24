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
    public List<CategoriaDto> list() {
        return categoriaService.listAll();
    }

    @GetMapping("/listarMostrar")
    public List<CategoriaDto> listOnlyActive() {
        return categoriaService.listOnlyActive();
    }

    @PostMapping("/guardar")
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }

    @PostMapping("/editar/{id}")
    public  Categoria edit(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        return  categoriaService.edit(id, categoria);
    }
}
