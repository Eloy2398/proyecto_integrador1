package com.apsolutions.controller;

import com.apsolutions.dto.website.CategoriaWebsiteDto;
import com.apsolutions.dto.website.MarcaWebsiteDto;
import com.apsolutions.dto.website.ProductoWebsiteDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.WebsiteService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/website")
public class WebsiteController {

    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/get-products-to-banner")
    public ApiResponse<List<ProductoWebsiteDto>> getProductsToBanner() {
        return websiteService.getProductsToBanner();
    }

    @GetMapping("/get-brands-main")
    public ApiResponse<List<MarcaWebsiteDto>> getBrandsMain() {
        return websiteService.getBrandsMain();
    }

    @GetMapping("/get-brands")
    public ApiResponse<List<MarcaWebsiteDto>> getBrands() {
        return websiteService.getBrands();
    }

    @GetMapping("/get-brands-category/{id}")
    public ApiResponse<List<MarcaWebsiteDto>> getBrands(@PathVariable("id") Integer id) {
        return websiteService.getBrandsCategory(id);
    }

    @GetMapping("/get-categories-main")
    public ApiResponse<List<CategoriaWebsiteDto>> getCategoriesMain() {
        return websiteService.getCategoriesMain();
    }

    @GetMapping("/get-categories")
    public ApiResponse<List<CategoriaWebsiteDto>> getCategories() {
        return websiteService.getCategories();
    }

    @GetMapping("/get-products-main")
    public ApiResponse<List<ProductoWebsiteDto>> getProductsMain() {
        return websiteService.getProductsMain();
    }

    @GetMapping("/get-products-category/{id}")
    public ApiResponse<Map<String, Object>> getProductsCategory(
            @PathVariable("id") Integer id,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "price", required = false) String strPriceRange,
            @RequestParam(name = "sortBy", required = false, defaultValue = "3") Integer sortBy) {
        return websiteService.getProductsCategory(id, brand, strPriceRange, sortBy);
    }

    @GetMapping("/validate-category")
    public ApiResponse<Categoria> validateCategory(@RequestParam Integer id, @RequestParam String urlName) {
        return websiteService.validateCategory(id, urlName);
    }

    @GetMapping("/get-product-data")
    public ApiResponse<ProductoWebsiteDto> getProductData(@RequestParam Integer id, @RequestParam String urlName) {
        return websiteService.getProductData(id, urlName);
    }
}
