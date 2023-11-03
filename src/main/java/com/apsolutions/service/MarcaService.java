package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Marca;
import com.apsolutions.repository.MarcaRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {
    private final MarcaRepository marcaRepository;

    public MarcaService(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    public ApiResponse<String> save(Marca marca) {
        if (marca.getId() == null) {
            marca.setId(0);
        }

        marca.setEstado(true);
        checkValidations(marca);
        marcaRepository.save(marca);

        return new ApiResponse<>(true, marca.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void checkValidations(Marca marca) {
        Optional<Marca> optionalMarca = marcaRepository.existsByName(marca.getNombre(), marca.getId());
        if (optionalMarca.isPresent()) {
            throw new CsException("La marca " + marca.getNombre() + " ya se encuentra registrada");
        }
    }

    public ApiResponse<List<Marca>> list() {
        return new ApiResponse<>(true, "OK", marcaRepository.list());
    }

    public ApiResponse<Marca> read(Integer id) {
        return new ApiResponse<>(true, "OK", marcaRepository.findById(id).orElse(null));
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!marcaRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        marcaRepository.updateStatus(false, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }
}
