package com.apsolutions.service;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ApiResponse<List<UsuarioDto>> list(){
        return new ApiResponse<>(true, "OK", usuarioRepository.list());
    }
}
