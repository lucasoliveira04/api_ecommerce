package ecommerce_api.ecommerce_api.repository;

import ecommerce_api.ecommerce_api.model.Product.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}