package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.util.ApiResponse;
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

    public ApiResponse<String> save(Categoria categoria) {
        checkValidations(categoria.getNombre(), 0);
        categoriaRepository.save(categoria);
        return new ApiResponse<>(true, "Se registró correctamente");
    }

    public ApiResponse<String> edit(Integer id, Categoria categoria) {
        categoria.setId(id);
        checkValidations(categoria.getNombre(), categoria.getId());
        categoriaRepository.save(categoria);
        return new ApiResponse<>(true, "Se modificó correctamente");
    }

    private void checkValidations(String name, int id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.existsByName(name, id);
        if (optionalCategoria.isPresent()) {
            throw new CsException("La categoría " + name + " ya se encuentra registrada");
        }
    }

    public ApiResponse<List<CategoriaDto>> list() {
        return new ApiResponse<>(true, "OK", categoriaMapper.toDto(categoriaRepository.list()));
    }

    public ApiResponse<String> delete(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CsException("No se encontró registro");
        }

        categoriaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente");
    }
}