package com.apsolutions.controller;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.service.CotizacionService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cotizacion")
public class CotizacionController {
    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<CotizacionDto>> list(){
        return cotizacionService.list();
    }

}
