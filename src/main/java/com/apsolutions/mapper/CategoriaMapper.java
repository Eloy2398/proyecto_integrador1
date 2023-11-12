package com.apsolutions.mapper;

import com.apsolutions.dto.CategoriaDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper extends GenericMapper<CategoriaDto, Categoria> {

    @Override
    CategoriaDto toDto(Categoria categoria);

    @Override
    Categoria toEntity(CategoriaDto categoriaDto);
}
