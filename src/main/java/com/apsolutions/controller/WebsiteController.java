package com.apsolutions.controller;

import com.apsolutions.dto.ProductoWebsiteDto;
import com.apsolutions.model.Categoria;
import com.apsolutions.service.WebsiteService;
import com.apsolutions.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/website")
public class WebsiteController {

    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/list-categories")
    public ApiResponse<List<Categoria>> listCategories() {
        return websiteService.listCategories();
    }

    @GetMapping("/get-products-to-banner")
    public ApiResponse<List<ProductoWebsiteDto>> getProductsToBanner() {
        return websiteService.getProductsToBanner();
    }
}
