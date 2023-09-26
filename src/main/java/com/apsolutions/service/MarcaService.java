package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Marca;
import com.apsolutions.repository.MarcaRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public ApiResponse<String> save(Marca marca) {
        checkValidations(marca.getNombre(), 0);
        marcaRepository.save(marca);
        return new ApiResponse<>(true, "Se registró correctamente");
    }

    public ApiResponse<String> edit(Integer id, Marca marca) {
        marca.setId(id);
        checkValidations(marca.getNombre(), marca.getId());
        marcaRepository.save(marca);
        return new ApiResponse<>(true, "se modificó correctamente");
    }

    private void checkValidations(String name, Integer id) {
        Optional<Marca> optionalMarca = marcaRepository.existsByName(name, id);
        if (optionalMarca.isPresent()) {
            throw new CsException("La marca " + name + " ya se encuentra registrada");
        }
    }

    public ApiResponse<List<Marca>> list() {
        return new ApiResponse<>(true, "OK", marcaRepository.list());
    }

    public ApiResponse<String> delete(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new CsException("No se encontró registro");
        }

        marcaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente");
    }
}
