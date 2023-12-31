package com.apsolutions.controller;

import com.apsolutions.dto.MarcaDto;
import com.apsolutions.model.Marca;
import com.apsolutions.service.MarcaService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/leer/{id}")
    public ApiResponse<Marca> read(@PathVariable("id") Integer id) {
        return marcaService.read(id);
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestPart("object") MarcaDto marcaDto, @RequestParam(value = "file", required = false) MultipartFile file) {
        marcaDto.setFile(file);
        return marcaService.save(marcaDto);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return marcaService.delete(id);
    }
}
