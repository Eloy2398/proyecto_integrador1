package com.apsolutions.mapper;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.dto.CriterioDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.model.Criterio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CriterioMapper extends GenericMapper<CriterioDto, Criterio> {

    @Override
    CriterioDto toDto(Criterio criterio);

    @Override
    Criterio toEntity(CriterioDto criterioDto);

}
