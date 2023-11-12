package com.apsolutions.service;

import com.apsolutions.dto.ProductoWebsiteDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.repository.CategoriaRepository;
import com.apsolutions.repository.ProductoRepository;
import com.apsolutions.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public ApiResponse<List<Categoria>> listCategories() {
        return new ApiResponse<>(true, "Ok", categoriaRepository.list());
    }

    public ApiResponse<List<ProductoWebsiteDto>> getProductsToBanner() {
        PageRequest pageRequest = PageRequest.of(1, 5);
        return new ApiResponse<>(true, "Ok", productoRepository.getProductsToBanner(pageRequest));
    }
}
