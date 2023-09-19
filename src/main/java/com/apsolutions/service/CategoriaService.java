package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public List<CategoriaDto> listOnlyActive() {
        return this.categoriaMapper.toDto(categoriaRepository.listOnlyActive());
    }

    public List<CategoriaDto> listAll() {
        return this.categoriaMapper.toDto(this.categoriaRepository.findAll());
    }

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }
}