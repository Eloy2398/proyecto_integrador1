package com.apsolutions.controller;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.dto.CriterioDto;
import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.CategoriaService;
import com.apsolutions.service.CriterioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/criterio")
public class CriterioController {
    @Autowired
    private CriterioService criterioService;

    @GetMapping("/listar")
    public List<CriterioDto> list() {
        return criterioService.findAll();
    }


}
