package com.apsolutions.controller;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.service.CategoriaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listar")
    public List<CategoriaDto> listarCategoriasActivas() {
        return  categoriaService.listarCategoriasActivas();
    }

    @GetMapping("/listAll")
    public List<CategoriaDto> listAll() {
        return  categoriaService.listAll();
    }
}
