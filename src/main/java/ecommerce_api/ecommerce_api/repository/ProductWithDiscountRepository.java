package ecommerce_api.ecommerce_api.repository;

import ecommerce_api.ecommerce_api.model.Product.ProductWithDiscountEntity;
import ecommerce_api.ecommerce_api.model.Product.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductWithDiscountRepository extends JpaRepository<ProductWithDiscountEntity, Long> {
    @Query("select p from product_main p order by p.id desc limit 1")
    Produto findLastInserted();

    boolean existsByProduto(Produto produto);
}