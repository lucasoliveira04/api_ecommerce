package ecommerce_api.ecommerce_api.services;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.dto.ProdutoDtoResponse;
import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;
import ecommerce_api.ecommerce_api.model.Produto;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import ecommerce_api.ecommerce_api.services.Discount.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatedProductService {
    private final ProdutoRepository produtoRepository;

    public CreatedProductService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ResponseEntity<?> create(ProdutoDto produtoDto) {
        try{
            Produto produto = new Produto();
            produto.setProductName(produtoDto.name());
            produto.setProductPrice(produtoDto.price());
            produto.setProductDescription(produtoDto.description());
            produto.setProductQuantity(produtoDto.productQuantity());
            produto.setCategoryProduct(produtoDto.categoryProduct());
            produto.setHasDiscount(produtoDto.hasDiscount());

            double priceWithDiscount = DiscountService.applyDiscount(
                    produto.getCategoryProduct(),
                    produto.getProductPrice(),
                    produtoDto.discount()
            );

            if (produtoDto.hasDiscount()) {
                produto.setProductPriceWithDiscount(priceWithDiscount);
            }

            Produto data =  produtoRepository.save(produto);

            ProdutoDtoResponse produtoDtoResponse = new ProdutoDtoResponse(data, priceWithDiscount, produtoDto.discount(), produtoDto.hasDiscount());

            return ResponseEntity.ok().body(produtoDtoResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
