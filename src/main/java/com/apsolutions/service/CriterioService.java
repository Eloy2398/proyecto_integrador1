package com.apsolutions.service;

import com.apsolutions.dto.CriterioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CriterioMapper;
import com.apsolutions.model.Criterio;
import com.apsolutions.repository.CriterioRepository;
import com.apsolutions.repository.CriterioopcionRepository;
import com.apsolutions.util.ApiResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CriterioService {

    private final CriterioRepository criterioRepository;
    private final CriterioopcionRepository criterioopcionRepository;
    private final CriterioMapper criterioMapper;

    public CriterioService(CriterioRepository criterioRepository, CriterioopcionRepository criterioopcionRepository, CriterioMapper criterioMapper) {
        this.criterioRepository = criterioRepository;
        this.criterioopcionRepository = criterioopcionRepository;
        this.criterioMapper = criterioMapper;
    }

    @Transactional
    public ApiResponse<String> save(CriterioDto criterioDto) {
        criterioDto.setId(0);
        processSaved(criterioDto);
        return new ApiResponse<>(true, "Se registró correctamente");
    }

    @Transactional
    public ApiResponse<String> edit(Integer id, CriterioDto criterioDto) {
        criterioDto.setId(id);
        processSaved(criterioDto);
        return new ApiResponse<>(true, "Se modificó correctamente");
    }

    private void processSaved(CriterioDto criterioDto) {
        Optional<Criterio> optionalCriterio = criterioRepository.existsByName(criterioDto.getNombre(), criterioDto.getId());
        if (optionalCriterio.isPresent()) {
            throw new CsException("El criterio " + criterioDto.getNombre() + " ya se encuentra registrado");
        }

        Criterio criterio = criterioRepository.save(criterioMapper.toEntity(criterioDto));

        criterioDto.getCriterioopcionList().forEach(criterioopcion -> {
            criterioopcion.setEstado(true);
            criterioopcion.setCriterio(criterio);
            criterioopcionRepository.save(criterioopcion);
        });
    }

    public ApiResponse<List<CriterioDto>> list() {
        List<Criterio> criterioList = criterioRepository.findAll();
        List<CriterioDto> criterioDtoList = new ArrayList<>();

        criterioList.forEach(criterio -> {
            CriterioDto criterioDto = criterioMapper.toDto(criterio);
            criterioDto.setCriterioopcionList(criterioopcionRepository.listByIdCriterio(criterio.getId()));
            criterioDtoList.add(criterioDto);
        });

        return new ApiResponse<>(true, "OK", criterioDtoList);
    }
}