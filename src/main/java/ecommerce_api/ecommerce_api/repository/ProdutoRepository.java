package ecommerce_api.ecommerce_api.repository;

import ecommerce_api.ecommerce_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}