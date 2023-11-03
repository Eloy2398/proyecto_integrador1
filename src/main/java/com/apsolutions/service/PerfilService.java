package com.apsolutions.service;

import com.apsolutions.exception.CsException;
import com.apsolutions.model.Perfil;
import com.apsolutions.repository.PerfilRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public ApiResponse<List<Perfil>> list() {
        return new ApiResponse<>(true, "OK", perfilRepository.list());
    }

    public void checkValidations(Perfil perfil) {
        Optional<Perfil> optionalPerfil = perfilRepository.existsByName(perfil.getNombre(), perfil.getId());
        if (optionalPerfil.isPresent()) {
            throw new CsException("El perfil " + perfil.getNombre() + " ya se encuentra registro.");
        }
    }

    public ApiResponse<String> save(Perfil perfil) {
        if (perfil.getId() == null) {
            perfil.setId(0);
        }

        perfil.setEstado(true);
        checkValidations(perfil);
        perfilRepository.save(perfil);
        return new ApiResponse<>(true, perfil.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    public ApiResponse<Perfil> read(Integer id) {
        return new ApiResponse<>(true, "OK", perfilRepository.findById(id).orElse(null));
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!perfilRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        perfilRepository.updateStatus(false, id);
        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }

}
