package com.apsolutions.controller;

import com.apsolutions.model.Categoria;
import com.apsolutions.service.CategoriaService;
import com.apsolutions.util.ApiResponse;
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
    public ApiResponse<List<Categoria>> list() {
        return categoriaService.list();
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<Categoria> list(@PathVariable("id") Integer id) {
        return categoriaService.read(id);
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }


    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return categoriaService.delete(id);
    }
}
