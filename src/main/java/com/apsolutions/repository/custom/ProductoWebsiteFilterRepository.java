package com.apsolutions.repository.custom;

import com.apsolutions.dto.website.ProductoDto;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductoWebsiteFilterRepository {

    List<ProductoDto> getAll(int idCategory, String idBrands, String strPriceRange, int sortBy);

    Long getTotalRecords(int idCategory, String idBrands, String strPriceRange);
}
