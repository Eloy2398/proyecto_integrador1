package com.apsolutions.controller;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
import com.apsolutions.dto.CotizaciondetalleDto;
import com.apsolutions.dto.query.CotizacionQueryDto;
import com.apsolutions.dto.query.PersonaQueryDto;
import com.apsolutions.dto.report.CotizacionReportDto;
import com.apsolutions.service.CotizacionService;
import com.apsolutions.util.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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
    public ApiResponse<Integer> save(@RequestBody CotizacionDto cotizacionDto) {
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

    @GetMapping("/obtenerDetalles/{id}")
    public ApiResponse<List<CotizaciondetalleDto>> getDetails(@PathVariable("id") Integer id) {
        return cotizacionService.getDetails(id);
    }

    @GetMapping("/reporte")
    public ApiResponse<List<CotizacionReportDto>> filter(
            @RequestParam(value = "fecha1", required = false) String fecha1,
            @RequestParam(value = "fecha2", required = false) String fecha2,
            @RequestParam(value = "idCliente", defaultValue = "0", required = false) Integer idCliente) {
        return cotizacionService.filter(fecha1, fecha2, idCliente);
    }

    @GetMapping("/reporte/excel")
    public ResponseEntity<Resource> excelReport(
            @RequestParam(value = "fecha1", required = false) String fecha1,
            @RequestParam(value = "fecha2", required = false) String fecha2,
            @RequestParam(value = "idCliente", defaultValue = "0", required = false) Integer idCliente) {
        return cotizacionService.excelReport(fecha1, fecha2, idCliente);
    }
}
