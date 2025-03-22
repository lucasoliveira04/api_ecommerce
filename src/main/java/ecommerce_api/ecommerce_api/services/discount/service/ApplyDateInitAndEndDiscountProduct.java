package ecommerce_api.ecommerce_api.services.discount.service;

import ecommerce_api.ecommerce_api.model.Product.ApplyDateLimitDiscountRequest;
import ecommerce_api.ecommerce_api.model.Product.ProductWithDiscountEntity;
import ecommerce_api.ecommerce_api.model.Product.Produto;
import ecommerce_api.ecommerce_api.repository.ProductWithDiscountRepository;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplyDateInitAndEndDiscountProduct {
    private final ProductWithDiscountRepository productWithDiscountRepository;
    private final ProdutoRepository produtoRepository;

    public ApplyDateInitAndEndDiscountProduct(ProductWithDiscountRepository productWithDiscountRepository, ProdutoRepository produtoRepository) {
        this.productWithDiscountRepository = productWithDiscountRepository;
        this.produtoRepository = produtoRepository;
    }

    public ResponseEntity<?> apply(ApplyDateLimitDiscountRequest request) {
        try {
            Optional<Produto> optional = produtoRepository.findById(request.getProdutoId());

            if (optional.isEmpty()) {
                return ResponseEntity.badRequest().body("Product not found.");
            }

            ProductWithDiscountEntity productWithDiscountEntity = new ProductWithDiscountEntity();
            productWithDiscountEntity.setProduto(optional.get());
            productWithDiscountEntity.setInitDiscount(request.getProductWithDiscountDto().initDiscount());
            productWithDiscountEntity.setEndDiscount(request.getProductWithDiscountDto().endDiscount());
            ProductWithDiscountEntity savedProductWithDiscountEntity = productWithDiscountRepository.save(productWithDiscountEntity);
            return ResponseEntity.ok().body(savedProductWithDiscountEntity);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
