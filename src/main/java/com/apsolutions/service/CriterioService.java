package com.apsolutions.service;

import com.apsolutions.dto.CriterioDto;
import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.mapper.CriterioMapper;
import com.apsolutions.mapper.CriterioopcionMapper;
import com.apsolutions.model.Criterio;
import com.apsolutions.model.Criterioopcion;
import com.apsolutions.repository.CriterioRepository;
import com.apsolutions.repository.CriterioopcionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CriterioService {

    private final CriterioRepository criterioRepository;
    private final CriterioopcionRepository criterioopcionRepository;
    private final CriterioMapper criterioMapper;
    private final CriterioopcionMapper criterioopcionMapper;

    public CriterioService(CriterioRepository criterioRepository, CriterioopcionRepository criterioopcionRepository, CriterioMapper criterioMapper, CriterioopcionMapper criterioopcionMapper) {
        this.criterioRepository = criterioRepository;
        this.criterioopcionRepository = criterioopcionRepository;
        this.criterioMapper = criterioMapper;
        this.criterioopcionMapper = criterioopcionMapper;
    }

    public Criterio save(CriterioDto criterioDto) {
        Criterio criterio = criterioRepository.save(criterioMapper.toEntity(criterioDto));

        criterioDto.getCriterioopcionList().forEach(criterioopcionDto -> {
            Criterioopcion criterioopcion = new Criterioopcion();
            criterioopcion.setEstado(true);
            criterioopcion.setCriterio(criterio);
            criterioopcion.setDescripcion(criterioopcionDto.getDescripcion());
            criterioopcionRepository.save(criterioopcion);
        });

        return criterio;
    }

    public Criterio edit(CriterioDto criterioDto, Integer id) {
        criterioDto.setId(id);
        Criterio criterio = criterioRepository.save(criterioMapper.toEntity(criterioDto));

        return null;
    }

    public List<CriterioDto> list() {
        List<Criterio> criterioList = criterioRepository.findAll();
        List<CriterioDto> criterioDtoList = new ArrayList<>();

        criterioList.forEach(criterio -> {
            CriterioDto criterioDto = criterioMapper.toDto(criterio);
            criterioDto.setCriterioopcionList(criterioopcionMapper.toDto(criterioopcionRepository.listByIdCriterio(criterio.getId())));
            criterioDtoList.add(criterioDto);
        });

        return criterioDtoList;
    }

    public List<CriterioopcionDto> listCriterioOpcionByIdCriterio(Integer idCriterio) {
        List<Criterioopcion> criterioopcionList = criterioopcionRepository.listFullByIdCriterio(idCriterio);
        return criterioopcionMapper.toDto(criterioopcionList);
    }

}