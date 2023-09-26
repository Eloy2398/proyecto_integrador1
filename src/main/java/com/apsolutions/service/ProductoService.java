package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.model.Producto;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ApiResponse<String> save(Producto producto) {
        productoRepository.save(producto);
        return new ApiResponse<>(true, "Se registró correctamente");
    }

    public ApiResponse<List<ProductoDto>> list() {
        return new ApiResponse<>(true, "OK", productoRepository.list());
    }
}
