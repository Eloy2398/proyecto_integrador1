package com.apsolutions.controller;

import com.apsolutions.dto.website.CategoriaDto;
import com.apsolutions.dto.website.MarcaDto;
import com.apsolutions.dto.website.ProductoDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.WebsiteService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/website")
public class WebsiteController {

    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/get-products-to-banner")
    public ApiResponse<List<ProductoDto>> getProductsToBanner() {
        return websiteService.getProductsToBanner();
    }

    @GetMapping("/get-brands-main")
    public ApiResponse<List<MarcaDto>> getBrandsMain() {
        return websiteService.getBrandsMain();
    }

    @GetMapping("/get-categories-main")
    public ApiResponse<List<CategoriaDto>> getCategoriesMain() {
        return websiteService.getCategoriesMain();
    }

    @GetMapping("/get-products-main")
    public ApiResponse<List<ProductoDto>> getProductsMain() {
        return websiteService.getProductsMain();
    }

    @GetMapping("/validate-category")
    public ApiResponse<Categoria> validateCategory(@RequestParam Integer id, @RequestParam String urlName) {
        return websiteService.validateCategory(id, urlName);
    }

    @GetMapping("/get-product-data")
    public ApiResponse<ProductoDto> getProductData(@RequestParam Integer id, @RequestParam String urlName) {
        return websiteService.getProductData(id, urlName);
    }
}
