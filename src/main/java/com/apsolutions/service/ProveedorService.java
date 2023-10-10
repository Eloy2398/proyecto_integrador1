package com.apsolutions.service;

import com.apsolutions.dto.ProveedorDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.model.Persona;
import com.apsolutions.model.Proveedor;
import com.apsolutions.repository.PersonaRepository;
import com.apsolutions.repository.ProveedorRepository;
import com.apsolutions.util.ApiResponse;
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
        Optional<Proveedor> proveedorOptional = proveedorRepository.existsByDocument(persona.getDocumento(), 0);
        if (proveedorOptional.isPresent()) {
            throw new CsException("El cliente con número de documento " + persona.getDocumento() + " existe.");
        }

        Optional<Persona> optionalPersona = personaRepository.existsByDocument(persona.getDocumento(), 0);
        if (optionalPersona.isPresent()) {
            persona = optionalPersona.get();
            Proveedor proveedor = new Proveedor();
            proveedor.setPersona(persona);
            proveedor.setEstado(true);
            proveedorRepository.save(proveedor);
            return new ApiResponse<>(true, "Se registro correctamente.");
        } else {
            persona = personaRepository.save(persona);

            Proveedor proveedor = new Proveedor();
            proveedor.setPersona(persona);
            proveedor.setEstado(true);
            proveedorRepository.save(proveedor);

            return new ApiResponse<>(true, "Se registro correctamente.");
        }
    }

    @Transactional
    public ApiResponse<String> edit(Integer id, Persona persona) {
        persona.setId(id);
        checkValidation(persona.getDocumento(), persona.getId());
        personaRepository.save(persona);
        return new ApiResponse<>(true, "Se modificó correctamente");
    }

    public ApiResponse<List<ProveedorDto>> list() {
        return new ApiResponse<>(true, "OK", proveedorRepository.list());
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!proveedorRepository.existsById(id)) {
            throw new CsException("No se encontró registro.");
        }

        proveedorRepository.updateStatus(false, id);

        return new ApiResponse<>(true, "Se eliminó correctamente.");
    }
}
