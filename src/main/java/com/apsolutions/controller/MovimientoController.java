package com.apsolutions.controller;

import com.apsolutions.dto.MovimientoDto;
import com.apsolutions.dto.MovimientoListDto;
import com.apsolutions.dto.query.PersonaDto;
import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.dto.report.MovimientoReportDto;
import com.apsolutions.service.MovimientoService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movimiento")
public class MovimientoController {

    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody MovimientoDto movimientoDto) {
        return movimientoService.save(movimientoDto);
    }

    @GetMapping("/listar")
    public ApiResponse<List<MovimientoListDto>> list() {
        return movimientoService.list();
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<MovimientoDto> read(@PathVariable("id") Integer id) {
        return movimientoService.read(id);
    }

    @PutMapping("/anular/{id}")
    public ApiResponse<String> unregister(@PathVariable("id") Integer id) {
        return movimientoService.unregister(id);
    }

    @GetMapping("/buscarPersona")
    public ApiResponse<List<PersonaDto>> searchPerson(@RequestParam String query) {
        return movimientoService.searchPerson(query);
    }

    @PostMapping("/reporte")
    public ApiResponse<List<MovimientoReportDto>> filter(@RequestBody MovimientoReportDto movimientoReportDto) {
        return movimientoService.filter(movimientoReportDto);
    }
}
