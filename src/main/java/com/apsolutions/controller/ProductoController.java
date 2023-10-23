package com.apsolutions.controller;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.dto.ProductoListDto;
import com.apsolutions.service.ProductoService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<ProductoListDto>> list() {
        return productoService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody ProductoDto productoDto) {
        return productoService.save(productoDto);
    }

    @GetMapping("/leer/{id}")
    public ApiResponse<ProductoDto> read(@PathVariable("id") Integer id) {
        return productoService.read(id);
    }

    @PutMapping("/eliminar/{id}")
    public ApiResponse<String> delete(@PathVariable("id") Integer id) {
        return productoService.delete(id);
    }

    @GetMapping("/cargarDatosExtra")
    public ApiResponse<Map<String, Object>> loadExtraData() {
        return productoService.loadExtraData();
    }
}
