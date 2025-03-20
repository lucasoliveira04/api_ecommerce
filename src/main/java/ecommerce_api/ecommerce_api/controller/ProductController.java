package ecommerce_api.ecommerce_api.controller;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.services.CreatedProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final CreatedProductService createdProductService;

    public ProductController(CreatedProductService createdProductService) {
        this.createdProductService = createdProductService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProdutoDto produtoDto) {
        return createdProductService.create(produtoDto);
    }
}
