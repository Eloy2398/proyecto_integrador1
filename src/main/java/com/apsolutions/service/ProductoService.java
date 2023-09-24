package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.repository.ProductoRepository;
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

    public List<ProductoDto> list() {
        return this.productoRepository.list();
    }

    //public Produ
}
