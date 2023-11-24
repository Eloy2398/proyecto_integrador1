package com.apsolutions.controller;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.query.CotizacionQueryDto;
import com.apsolutions.dto.query.PersonaQueryDto;
import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.service.CotizacionService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cotizacion")
public class CotizacionController {
    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody CotizacionDto cotizacionDto) {
        return cotizacionService.save(cotizacionDto);
    }

    @GetMapping("/listar")
    public ApiResponse<List<CotizacionListDto>> list() {
        return cotizacionService.list();
    }

    @PutMapping("/anular/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return cotizacionService.anular(id);
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<CotizacionDto> read(@PathVariable("id") Integer id) {
        return cotizacionService.read(id);
    }

    @GetMapping("/buscar")
    public ApiResponse<List<CotizacionQueryDto>> search(@RequestParam String query) {
        return cotizacionService.search(query);
    }

    @GetMapping("/buscarCliente")
    public ApiResponse<List<PersonaQueryDto>> searchClient(@RequestParam String query) {
        return cotizacionService.searchClient(query);
    }

    @PostMapping("/reporte")
    public ApiResponse<List<CotizacionReportDto>> filter(@RequestBody CotizacionReportDto cotizacionReportDto) {
        return cotizacionService.filter(cotizacionReportDto);
    }

}
