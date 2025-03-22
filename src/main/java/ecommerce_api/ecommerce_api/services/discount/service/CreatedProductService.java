package ecommerce_api.ecommerce_api.services.discount.service;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.dto.ProdutoDtoResponse;
import ecommerce_api.ecommerce_api.model.Product.Produto;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import ecommerce_api.ecommerce_api.services.discount.types.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreatedProductService {
    private final ProdutoRepository produtoRepository;
    private final DiscountService discountService;

    public CreatedProductService(ProdutoRepository produtoRepository, DiscountService discountService) {
        this.produtoRepository = produtoRepository;
        this.discountService = discountService;
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

            produto.applyDiscount(discountService, produtoDto.discount());

            Produto data =  produtoRepository.save(produto);

            ProdutoDtoResponse produtoDtoResponse = new ProdutoDtoResponse(
                    data,
                    produto.getProductPriceWithDiscount(),
                    produtoDto.discount(),
                    produtoDto.hasDiscount()
            );

            return ResponseEntity.ok().body(produtoDtoResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
