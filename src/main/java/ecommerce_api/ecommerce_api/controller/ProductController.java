package ecommerce_api.ecommerce_api.controller;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.services.CreatedProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:5173")
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
