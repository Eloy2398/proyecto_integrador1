package com.apsolutions.controller;

import com.apsolutions.dto.CriterioDto;
import com.apsolutions.dto.ProductoDto;
import com.apsolutions.model.Criterio;
import com.apsolutions.service.CriterioService;
import com.apsolutions.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/criterio")
public class CriterioController {
    @Autowired
    private CriterioService criterioService;

    @GetMapping("/listar")
    public ApiResponse<List<CriterioDto>> list() {
        return criterioService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody CriterioDto criterioDto) {
        return criterioService.save(criterioDto);
    }

    @PutMapping("/editar/{id}")
    public ApiResponse<String> edit(@PathVariable("id") Integer id, @RequestBody CriterioDto criterioDto) {
        return criterioService.edit(id, criterioDto);
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<CriterioDto> read(@PathVariable("id") Integer id) {
        return criterioService.read(id);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return criterioService.delete(id);
    }
}
