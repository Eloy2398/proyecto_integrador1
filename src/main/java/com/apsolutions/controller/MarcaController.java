package com.apsolutions.controller;

import com.apsolutions.model.Marca;
import com.apsolutions.service.MarcaService;
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
    public List<Marca> list() {
        return marcaService.list();
    }

    @PostMapping("/guardar")
    public Marca save(@RequestBody Marca marca) {
        return marcaService.save(marca);
    }

    @PostMapping("/editar/{id}")
    public Marca edit(@PathVariable("id") Integer id, @RequestBody Marca marca) {
        return marcaService.edit(id, marca);
    }
}
