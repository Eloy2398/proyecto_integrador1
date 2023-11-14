package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.FileStorage;
import com.apsolutions.util.Global;
import com.apsolutions.util.URLNormalizer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public ApiResponse<String> save(CategoriaDto categoriaDto) {
        checkValidations(categoriaDto);

        String filenameImage = "";

        if (categoriaDto.getId() == null) {
            categoriaDto.setId(0);

            filenameImage = categoriaRepository.getImage(categoriaDto.getId());
            if (categoriaDto.getFile() == null) {
                fileStorage.delete(filenameImage, Global.DIR_CATEGORIES);
            }
        }

        categoriaDto.setEstado(true);

        Categoria categoriaTmp = categoriaMapper.toEntity(categoriaDto);
        categoriaTmp.setNombreUrl(URLNormalizer.encode(categoriaTmp.getNombre()));
        categoriaTmp.setImagen(fileStorage.upload(categoriaDto.getFile(), Global.DIR_CATEGORIES, filenameImage));
        categoriaTmp.setMostrardestacado(categoriaDto.getMostrardestacado());
        categoriaTmp.setMostrarweb(categoriaDto.getMostrarweb());
        categoriaRepository.save(categoriaTmp);
        return new ApiResponse<>(true, categoriaTmp.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void checkValidations(CategoriaDto categoriaDto) {
        Optional<Categoria> optionalCategoria = categoriaRepository.existsByName(categoriaDto.getNombre(), categoriaDto.getId());
        if (optionalCategoria.isPresent()) {
            throw new CsException("La categoría " + categoriaDto.getNombre() + " ya se encuentra registrada");
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