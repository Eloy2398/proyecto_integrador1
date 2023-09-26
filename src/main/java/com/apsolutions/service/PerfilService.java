package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Perfil;
import com.apsolutions.repository.PerfilRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public ApiResponse<List<Perfil>> list() {
        return new ApiResponse<>(true, "OK", perfilRepository.list());
    }

    public void checkValidations(String name, int id) {
        Optional<Perfil> optionalPerfil = perfilRepository.existsByName(name, id);
        if (optionalPerfil.isPresent()) {
            throw new CsException("El perfil " + name + " ya se encuentra registro.");
        }
    }

    public ApiResponse<String> save(Perfil perfil) {
        checkValidations(perfil.getNombre(), 0);
        perfilRepository.save(perfil);
        return new ApiResponse<>(true, "Registrado correctamente.");
    }

}
