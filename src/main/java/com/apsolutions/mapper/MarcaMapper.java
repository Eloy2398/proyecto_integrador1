package com.apsolutions.mapper;

import com.apsolutions.dto.MarcaDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Marca;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper extends GenericMapper<MarcaDto, Marca> {

    @Override
    MarcaDto toDto(Marca marca);

    @Override
    Marca toEntity(MarcaDto marcaDto);

}
