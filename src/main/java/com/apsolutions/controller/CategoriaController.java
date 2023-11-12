package com.apsolutions.controller;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.CategoriaService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ApiResponse<Categoria> read(@PathVariable("id") Integer id) {
        return categoriaService.read(id);
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestPart("object") CategoriaDto categoriaDto, @RequestParam(value = "file", required = false) MultipartFile file) {
        categoriaDto.setFile(file);
        return categoriaService.save(categoriaDto);
    }


    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return categoriaService.delete(id);
    }
}
