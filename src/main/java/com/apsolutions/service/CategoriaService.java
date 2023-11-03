package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ApiResponse<String> save(Categoria categoria) {
        if (categoria.getId() == null) {
            categoria.setId(0);
        }

        categoria.setEstado(true);
        checkValidations(categoria);
        categoriaRepository.save(categoria);
        return new ApiResponse<>(true, categoria.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void checkValidations(Categoria categoria) {
        Optional<Categoria> optionalCategoria = categoriaRepository.existsByName(categoria.getNombre(), categoria.getId());
        if (optionalCategoria.isPresent()) {
            throw new CsException("La categoría " + categoria.getNombre() + " ya se encuentra registrada");
        }
    }

    public ApiResponse<List<Categoria>> list() {
        return new ApiResponse<>(true, "OK", categoriaRepository.list());
    }

    public ApiResponse<Categoria> read(Integer id) {
        return new ApiResponse<>(true, "OK", categoriaRepository.findById(id).orElse(null));
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        categoriaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }
}