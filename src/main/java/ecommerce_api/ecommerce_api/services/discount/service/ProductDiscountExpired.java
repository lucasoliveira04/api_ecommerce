package ecommerce_api.ecommerce_api.services.discount.service;

import ecommerce_api.ecommerce_api.model.Product.ProductWithDiscountEntity;
import ecommerce_api.ecommerce_api.repository.ProductWithDiscountRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductDiscountExpired {

    private final ProductWithDiscountRepository productWithDiscountRepository;

    public ProductDiscountExpired(ProductWithDiscountRepository productWithDiscountRepository) {
        this.productWithDiscountRepository = productWithDiscountRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void checkDateInitialisationAndEndDiscount() {
        List<ProductWithDiscountEntity> productWithDiscountEntities = this.productWithDiscountRepository.findAll();

        for (ProductWithDiscountEntity productWithDiscountEntity : productWithDiscountEntities) {
            if (productWithDiscountEntity.getEndDiscount().isBefore(LocalDateTime.now())){
                System.out.println("Discount expired at product: " + productWithDiscountEntity.getProduto().getProductName());
                this.productWithDiscountRepository.delete(productWithDiscountEntity);
                productWithDiscountEntity.getProduto().setHasDiscount(false);
                this.productWithDiscountRepository.save(productWithDiscountEntity);
            }
        }
    }
}
