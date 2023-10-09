package com.apsolutions.mapper;

import com.apsolutions.dto.PersonaDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Persona;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface PersonaMapper extends GenericMapper<PersonaDto, Persona> {
    @Override
    Persona toEntity(PersonaDto personaDto);
}
