package com.apsolutions.mapper;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.dto.CriterioopcionDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Categoria;
import com.apsolutions.model.Criterioopcion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CriterioopcionMapper extends GenericMapper<CriterioopcionDto, Criterioopcion> {

    @Override
    CriterioopcionDto toDto(Criterioopcion criterioopcion);

    @Override
    Criterioopcion toEntity(CriterioopcionDto criterioopcionDto);

}
