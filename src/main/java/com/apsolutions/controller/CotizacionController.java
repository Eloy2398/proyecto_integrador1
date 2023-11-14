package com.apsolutions.controller;

import com.apsolutions.dto.ClienteBusquedaDto;
import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.dto.CotizacionListDto;
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

    @GetMapping("/buscarCliente")
    public ApiResponse<List<ClienteBusquedaDto>> searchClient(@RequestParam String query) {
        return cotizacionService.searchClient(query);
    }

}
