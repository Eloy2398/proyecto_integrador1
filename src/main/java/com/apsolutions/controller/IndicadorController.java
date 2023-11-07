package com.apsolutions.controller;

import com.apsolutions.dto.IndicadorProductoDto;
import com.apsolutions.service.IndicadorService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/indicador")
public class IndicadorController {

    private final IndicadorService indicadorService;

    public IndicadorController(IndicadorService indicadorService) {
        this.indicadorService = indicadorService;
    }

    @GetMapping("/filtros")
    public ApiResponse<Map<String, Object>> filtros() {
        return indicadorService.filtros();
    }

    @GetMapping("/totales")
    public ApiResponse<Map<String, Object>> totales() {
        return indicadorService.totales();
    }

    @GetMapping("/stockproductos")
    public ApiResponse<List<IndicadorProductoDto>> stockproductos(
            @RequestParam(value = "idCategoria", defaultValue = "0") Integer idCategoria,
            @RequestParam(value = "valOrden", defaultValue = "1") Integer valOrden) {
        return indicadorService.stockproductos(idCategoria, valOrden);
    }

    @GetMapping("/cotizaciones")
    public ApiResponse<Map<String, Object>> cotizaciones() {
        return indicadorService.cotizaciones();
    }
}
