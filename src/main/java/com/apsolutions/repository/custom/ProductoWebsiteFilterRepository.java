package com.apsolutions.repository.custom;

import com.apsolutions.dto.website.ProductoWebsiteDto;

import java.util.List;

public interface ProductoWebsiteFilterRepository {

    List<ProductoWebsiteDto> getAll(int idCategory, String idBrands, String strPriceRange, int sortBy);

    Long getTotalRecords(int idCategory, String idBrands, String strPriceRange);
}
