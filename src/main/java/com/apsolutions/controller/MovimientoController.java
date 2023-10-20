package com.apsolutions.controller;

import com.apsolutions.model.Movimiento;
import com.apsolutions.service.MovimientoService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Movimiento movimiento) {
        return movimientoService.save(movimiento);
    }
}
