package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.util.ApiResponse;
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

    public ApiResponse<List<Categoria>> list() {
        return new ApiResponse<>(true, "OK", categoriaRepository.list());
    }

    public ApiResponse<String> delete(Integer id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CsException("No se encontró registro");
        }

        categoriaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente");
    }
}