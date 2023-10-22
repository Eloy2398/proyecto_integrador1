package com.apsolutions.mapper;

import com.apsolutions.dto.MovimientoDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Movimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper extends GenericMapper<MovimientoDto, Movimiento> {
    @Override
    MovimientoDto toDto(Movimiento movimiento);

    @Override
    Movimiento toEntity(MovimientoDto movimientoDto);
}
