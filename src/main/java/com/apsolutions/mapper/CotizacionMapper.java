package com.apsolutions.mapper;

import com.apsolutions.dto.CotizacionDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Cotizacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CotizacionMapper extends GenericMapper<CotizacionDto, Cotizacion> {

    @Override
    CotizacionDto toDto(Cotizacion cotizacion);

    @Override
    Cotizacion toEntity(CotizacionDto cotizacionDto);
}
