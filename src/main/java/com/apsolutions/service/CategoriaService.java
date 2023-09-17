package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
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

    public List<CategoriaDto> listarCategoriasActivas() {
        List<Categoria> categoriaIterable = categoriaRepository.listarCategoriasActivas();

        return categoriaMapper.toDto(categoriaIterable);
    }

    public List<CategoriaDto> listAll() {
        Iterable<Categoria> categoriaIterable = categoriaRepository.listAll();

        List<CategoriaDto> listaCategoriaDto = new LinkedList<>();
        categoriaIterable.forEach(categoria -> {
            CategoriaDto categoriaDto = categoriaMapper.toDto(categoria);
            listaCategoriaDto.add(categoriaDto);
        });

        return listaCategoriaDto;
    }
}
