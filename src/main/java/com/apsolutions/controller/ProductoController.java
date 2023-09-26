package com.apsolutions.controller;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.model.Producto;
import com.apsolutions.service.ProductoService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listar")
    public ApiResponse<List<ProductoDto>> list() {
        return productoService.list();
    }

    @PostMapping("/guardar")
    public ApiResponse<String> save(@RequestBody Producto producto) {
        return productoService.save(producto);
    }
}
