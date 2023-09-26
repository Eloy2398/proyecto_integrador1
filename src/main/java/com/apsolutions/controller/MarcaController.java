package com.apsolutions.controller;

import com.apsolutions.model.Marca;
import com.apsolutions.service.MarcaService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/marca")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<Marca>> list() {
        return marcaService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Marca marca) {
        return marcaService.save(marca);
    }

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody Marca marca) {
        return marcaService.edit(id, marca);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id) {
        return marcaService.delete(id);
    }
}
