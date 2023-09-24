package com.apsolutions.service;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.dto.CriterioDto;
import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.mapper.CategoriaMapper;
import com.apsolutions.mapper.CriterioMapper;
import com.apsolutions.mapper.CriterioopcionMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.model.Criterio;
import com.apsolutions.model.Criterioopcion;
import com.apsolutions.repository.CategoriaRepository;
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

    public List<CriterioDto> findAll(){
        List<Criterio> criterios = criterioRepository.findAll();
        List<CriterioDto> criterioDto = new ArrayList<>();
        for (Criterio criterio : criterios) {
            Integer idCriterio = criterio.getId();  // Obtener el ID del criterio
            List<CriterioopcionDto> criterioOpcionDtos = listarCriterioOpcionByIdCriterio(idCriterio);
            criterioDto.get(0).setId(criterio.getId());
            criterioDto.get(0).setEstado(criterio.getEstado());
            criterioDto.get(0).setNombre(criterio.getNombre());
            criterioDto.get(0).setCriterioopcionDtoList(criterioOpcionDtos);
        }
        return criterioDto;
    }


    public List<CriterioopcionDto> listarCriterioOpcionByIdCriterio(Integer idCriterio) {
        List<Criterioopcion> criterioopcions = criterioopcionRepository.listCriterioOpcionById(idCriterio);
        return criterioopcionMapper.toDto(criterioopcions);
    }

}