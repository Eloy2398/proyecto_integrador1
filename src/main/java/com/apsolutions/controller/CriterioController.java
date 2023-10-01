package com.apsolutions.controller;

import com.apsolutions.dto.CriterioDto;
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
}
