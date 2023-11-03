package com.apsolutions.service;

import com.apsolutions.dto.UsuarioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Usuario;
import com.apsolutions.repository.UsuarioRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @Autowired
    private TokenService tokenService;

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
        usuario.setBloqueado(Byte.parseByte("0"));
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    public ApiResponse<String> edit(Integer id, Usuario usuario) {
        usuario.setId(id);
        checkValidations(usuario.getUsuario(), usuario.getId());
        usuarioRepository.save(usuario);
        return new ApiResponse<>(true, Global.SUCCESSFUL_UPDATE_MESSAGE);
    }

    public ApiResponse<String> delete(Integer id, HttpServletRequest request) {
        Optional<Usuario> optionalUsuario = usuarioRepository.searchById(id);
        if (optionalUsuario.isPresent()) {
            Usuario userLogin = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
            if (userLogin.getId().equals(id)) {
                throw new CsException("No es posible eliminar el usuario con el que está logeado.");
            }

            if (optionalUsuario.get().getPerfil().getId() == 1) {
                throw new CsException("No es posible eliminar al usuario por ser administrador.");
            }

            usuarioRepository.updateStatus(false, id);
            return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
        } else {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }
    }

    public ApiResponse<String> bloquear(Integer id, HttpServletRequest request) {
        Optional<Usuario> optionalUsuario = usuarioRepository.searchById(id);
        if (optionalUsuario.isPresent()) {
            Usuario userLogin = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
            if (userLogin.getId().equals(id)) {
                throw new CsException("No es posible bloquear el usuario con el que está logeado.");
            }

            byte locked;
            if (optionalUsuario.get().getBloqueado() == 1) {
                locked = 0;
            } else {
                locked = 1;
            }

            usuarioRepository.updateBloqueo(id, locked);
            return new ApiResponse<>(true, "Se " + (locked == 1 ? "bloqueó" : "desbloqueó") + " correctamente.");
        } else {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }
    }

    public ApiResponse<UsuarioDto> getAuthenticatedUser(HttpServletRequest request) {
        Usuario usuario = tokenService.getUserByToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        UsuarioDto usuarioDto = new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.getUsuario(), usuario.getBloqueado(), usuario.getPerfil().getNombre(), usuario.getPerfil().getId());
        return new ApiResponse<>(true, "Ok", usuarioDto);
    }
}
