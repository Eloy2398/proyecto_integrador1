package com.apsolutions.service;

import com.apsolutions.dto.UsuarioPerfilDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.UsuarioPerfilRepository;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioPerfilService {

    private final UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UsuarioPerfilService(UsuarioPerfilRepository usuarioPerfilRepository) {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private void validationPassword(String textPassword, String passwordDB) {
        if (!verifyPassword(textPassword, passwordDB)) {
            throw new CsException("La contraseña actual no coincide.");
        }
    }

    private void checkValidations(String use, int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.existsByUsername(use, id);
        if (optionalUsuario.isPresent()) {
            throw new CsException("El usuario " + use + " ya se encuentra registrado.");
        }
    }

    @Transactional
    public ApiResponse<String> updateUsernamePassword(UsuarioPerfilDto usuario, HttpServletRequest request) {
        Usuario userLogin = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (!usuario.getUsuario().isEmpty()) {
            checkValidations(usuario.getUsuario(), userLogin.getId());
            usuarioPerfilRepository.updateUsername(usuario.getUsuario(), userLogin.getId());
        }

        if (!usuario.getClaveNueva().isEmpty()) {
            Optional<Usuario> optionalUsuario = usuarioRepository.searchById(userLogin.getId());

            if (optionalUsuario.isPresent()) {
                String claveActualLogin = optionalUsuario.get().getClave();
                usuario.setClaveNueva(passwordEncoder.encode(usuario.getClaveNueva()));
                validationPassword(usuario.getClaveActual(), claveActualLogin);
                usuarioPerfilRepository.updatePassword(usuario.getClaveNueva(), userLogin.getId());
            } else {
                throw new CsException(Global.REGISTER_NOT_FOUND);
            }
        }

        return new ApiResponse<>(true, Global.SUCCESSFUL_UPDATE_MESSAGE);
    }
}
