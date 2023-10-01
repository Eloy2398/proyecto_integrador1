package com.apsolutions.mapper;

import com.apsolutions.dto.ProductoDto;
import com.apsolutions.mapperimpl.GenericMapper;
import com.apsolutions.model.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper extends GenericMapper<ProductoDto, Producto> {

    @Override
    ProductoDto toDto(Producto producto);

    @Override
    Producto toEntity(ProductoDto productoListDto);
}
