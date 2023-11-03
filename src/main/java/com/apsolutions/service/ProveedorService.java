package com.apsolutions.service;

import com.apsolutions.dto.ProveedorDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Persona;
import com.apsolutions.model.Proveedor;
import com.apsolutions.repository.PersonaRepository;
import com.apsolutions.repository.ProveedorRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {
    private final PersonaRepository personaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    public ProveedorService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    private void checkValidation(String documento, Integer id) {
        Optional<Persona> optionalPersona = personaRepository.existsByDocument(documento, id);

        if (optionalPersona.isPresent()) {
            throw new CsException("El número de documento " + documento + " existe.");
        }
    }

    @Transactional
    public ApiResponse<String> save(Persona persona) {
        if (persona.getId() == null) {
            persona.setId(0);
        }

        if (persona.getId() > 0) {
            checkValidation(persona.getDocumento(), persona.getId());
            personaRepository.save(persona);
            return new ApiResponse<>(true, Global.SUCCESSFUL_UPDATE_MESSAGE);
        } else {
            Optional<Proveedor> proveedorOptional = proveedorRepository.existsByDocument(persona.getDocumento(), 0);
            if (proveedorOptional.isPresent()) {
                throw new CsException("El proveedor con número de documento " + persona.getDocumento() + " existe.");
            }

            Optional<Persona> optionalPersona = personaRepository.existsByDocument(persona.getDocumento(), 0);
            if (optionalPersona.isPresent()) {
                persona = optionalPersona.get();
            } else {
                persona = personaRepository.save(persona);
            }

            Proveedor proveedor = new Proveedor();
            proveedor.setPersona(persona);
            proveedor.setEstado(true);
            proveedorRepository.save(proveedor);
            return new ApiResponse<>(true, Global.SUCCESSFUL_INSERT_MESSAGE);
        }
    }

    public ApiResponse<Persona> read(Integer id) {
        return new ApiResponse<>(true, "OK", personaRepository.findById(id).orElse(null));
    }

    public ApiResponse<List<ProveedorDto>> list() {
        return new ApiResponse<>(true, "OK", proveedorRepository.list());
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        Optional<Proveedor> optionalProveedor = proveedorRepository.obtenerById(id);
        if (optionalProveedor.isPresent()) {
            proveedorRepository.updateStatus(false, optionalProveedor.get().getId());
            return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
        } else {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }
    }
}
