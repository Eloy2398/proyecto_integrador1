package com.apsolutions.service;

import com.apsolutions.dto.CriterioDto;
import com.apsolutions.exception.CsException;
import com.apsolutions.mapper.CriterioMapper;
import com.apsolutions.mapper.CriterioopcionMapper;
import com.apsolutions.model.Criterio;
import com.apsolutions.model.Criterioopcion;
import com.apsolutions.repository.CriterioRepository;
import com.apsolutions.repository.CriterioopcionRepository;
import com.apsolutions.util.ApiResponse;
import com.apsolutions.util.Global;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CriterioService {

    private final CriterioRepository criterioRepository;
    private final CriterioopcionRepository criterioopcionRepository;
    private final CriterioMapper criterioMapper;
    @Autowired
    private CriterioopcionMapper criterioopcionMapper;

    public CriterioService(CriterioRepository criterioRepository, CriterioopcionRepository criterioopcionRepository, CriterioMapper criterioMapper) {
        this.criterioRepository = criterioRepository;
        this.criterioopcionRepository = criterioopcionRepository;
        this.criterioMapper = criterioMapper;
    }

    @Transactional
    public ApiResponse<String> save(CriterioDto criterioDto) {
        if (criterioDto.getId() == null) {
            criterioDto.setId(0);
        }

        criterioDto.setEstado(true);
        processSaved(criterioDto);

        return new ApiResponse<>(true, criterioDto.getId() > 0 ? Global.SUCCESSFUL_UPDATE_MESSAGE : Global.SUCCESSFUL_INSERT_MESSAGE);
    }

    private void processSaved(CriterioDto criterioDto) {
        Optional<Criterio> optionalCriterio = criterioRepository.existsByName(criterioDto.getNombre(), criterioDto.getId());
        if (optionalCriterio.isPresent()) {
            throw new CsException("El criterio " + criterioDto.getNombre() + " ya se encuentra registrado.");
        }

        if (criterioDto.getId() > 0) {
            criterioopcionRepository.updateStatusByCriterio(false, criterioDto.getId());
        }

        Criterio criterio = criterioRepository.save(criterioMapper.toEntity(criterioDto));

        criterioDto.getCriterioopcionList().forEach(criterioopcion -> {
            criterioopcion.setEstado(true);
            criterioopcion.setCriterio(criterio);
            Optional<Criterioopcion> optionalCriterioopcion = criterioopcionRepository.obtenerByDescripcion(criterioDto.getId(), criterioopcion.getDescripcion());
            if (optionalCriterioopcion.isPresent()) {
                criterioopcionRepository.updateStatus(true, optionalCriterioopcion.get().getId());
            } else {
                criterioopcionRepository.save(criterioopcion);
            }
        });
    }

    public ApiResponse<List<CriterioDto>> list() {
        List<CriterioDto> criterioDtoList = criterioMapper.toDto(criterioRepository.list());

        criterioDtoList.forEach(criterioDto -> {
            criterioDto.setCriterioopcionList(criterioopcionMapper.toEntity(criterioopcionRepository.listByIdCriterio(criterioDto.getId())));
        });

        return new ApiResponse<>(true, "OK", criterioDtoList);
    }

    public ApiResponse<CriterioDto> read(Integer id) {
        CriterioDto criterioDto = criterioMapper.toDto(criterioRepository.findById(id).orElse(null));
        criterioDto.setCriterioopcionList(criterioopcionMapper.toEntity(criterioopcionRepository.listByIdCriterio(id)));

        return new ApiResponse<>(true, "Ok", criterioDto);
    }

    @Transactional
    public ApiResponse<String> delete(Integer id) {
        if (!criterioRepository.existsById(id)) {
            throw new CsException(Global.REGISTER_NOT_FOUND);
        }

        criterioRepository.updateStatus(false, id);

        return new ApiResponse<>(true, Global.SUCCESSFUL_DELETE_MESSAGE);
    }

}