package com.apsolutions.service;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ApiResponse<List<UsuarioDto>> list() {
        return new ApiResponse<>(true, "OK", usuarioRepository.list());
    }

    private void checkValidations(String use, int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.existsByUsername(use, id);
        if (optionalUsuario.isPresent()) {
            throw new CsException("El usuario " + use + " ya se encuentra registrado.");
        }
    }

    public ApiResponse<String> save(Usuario usuario) {
        checkValidations(usuario.getUsuario(), 0);
        usuario.setClave(passwordEncoder.encode(usuario.getClave()));
        usuario.setEstado(true);
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, "Se registro correctamente.");
    }

    public ApiResponse<String> edit(Integer id, Usuario usuario) {
        usuario.setId(id);
        checkValidations(usuario.getUsuario(), usuario.getId());
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, "Se modificó correctamente.");
    }

    public ApiResponse<String> delete(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new CsException("No se encontró registro.");
        }

        usuarioRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente.");
    }
}
