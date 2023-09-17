package com.apsolutions.service;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.mapper.ProductoMapper;
import com.apsolutions.model.Producto;
import com.apsolutions.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDto> listAll() {
        return this.productoMapper.toDto(this.productoRepository.listar());
        //return this.productoMapper.toDto(this.productoRepository.findAll());
    }
}
