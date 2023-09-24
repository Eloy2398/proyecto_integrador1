package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Categoria> optionalCategoria = categoriaRepository.existsByName(categoria.getNombre(), categoria.getId());
        if (optionalCategoria.isPresent()) {
            //return
        }

        return this.categoriaRepository.save(categoria);
    }

    public Categoria edit(Integer id, Categoria categoria) {
        categoria.setId(id);
        return save(categoria);
    }

}