package ecommerce_api.ecommerce_api.controller;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.model.Product.ApplyDateLimitDiscountRequest;
import ecommerce_api.ecommerce_api.services.discount.service.ApplyDateInitAndEndDiscountProduct;
import ecommerce_api.ecommerce_api.services.discount.service.CreatedProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
    private final CreatedProductService createdProductService;
    private final ApplyDateInitAndEndDiscountProduct applyDateInitAndEndDiscountProduct;

    public ProductController(CreatedProductService createdProductService, ApplyDateInitAndEndDiscountProduct applyDateInitAndEndDiscountProduct) {
        this.createdProductService = createdProductService;
        this.applyDateInitAndEndDiscountProduct = applyDateInitAndEndDiscountProduct;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProdutoDto produtoDto) {
        return createdProductService.create(produtoDto);
    }

    @PostMapping("/apply/limit/date/discount")
    public ResponseEntity<?> applyDiscount(@RequestBody ApplyDateLimitDiscountRequest request) {
        return applyDateInitAndEndDiscountProduct.apply(request);
    }
}
