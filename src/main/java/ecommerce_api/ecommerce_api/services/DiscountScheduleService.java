package ecommerce_api.ecommerce_api.services;

import ecommerce_api.ecommerce_api.model.ProductWithDiscountEntity;
import ecommerce_api.ecommerce_api.repository.ProductWithDiscountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountScheduleService {
    private final ProductWithDiscountRepository productWithDiscountRepository;

    public DiscountScheduleService(ProductWithDiscountRepository productWithDiscountRepository) {
        this.productWithDiscountRepository = productWithDiscountRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void applicationDateInitialisationAndEndDiscount() {
        if (this.productWithDiscountRepository.findLastInserted().isHasDiscount() && !this.productWithDiscountRepository.existsByProduto(this.productWithDiscountRepository.findLastInserted())){
            ProductWithDiscountEntity productWithDiscountEntity = new ProductWithDiscountEntity();
            productWithDiscountEntity.setProduto(this.productWithDiscountRepository.findLastInserted());
            productWithDiscountEntity.setInitDiscount(LocalDateTime.now());
            productWithDiscountEntity.setEndDiscount(LocalDateTime.now().plusMonths(1));
            this.productWithDiscountRepository.save(productWithDiscountEntity);
            System.out.println("Date init: " + productWithDiscountEntity.getInitDiscount() + " End: " + productWithDiscountEntity.getEndDiscount());
            return;
        }

        System.out.println("Product by date initialisation");
    }

    @Scheduled(fixedRate = 60000)
    public void checkDateInitialisationAndEndDiscount() {
        List<ProductWithDiscountEntity> productWithDiscountEntities = this.productWithDiscountRepository.findAll();

        for (ProductWithDiscountEntity productWithDiscountEntity : productWithDiscountEntities) {
            if (productWithDiscountEntity.getEndDiscount().isBefore(LocalDateTime.now())){
                System.out.println("Desconto expirado para: " + productWithDiscountEntity.getProduto().getProductName());
                this.productWithDiscountRepository.delete(productWithDiscountEntity);
                productWithDiscountEntity.getProduto().setHasDiscount(false);
                this.productWithDiscountRepository.save(productWithDiscountEntity);
            }
        }
    }
}
