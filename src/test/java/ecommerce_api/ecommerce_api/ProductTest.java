package ecommerce_api.ecommerce_api;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.dto.ProdutoDtoResponse;
import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;
import ecommerce_api.ecommerce_api.model.Product.Produto;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import ecommerce_api.ecommerce_api.services.discount.service.CreatedProductService;
import ecommerce_api.ecommerce_api.services.discount.types.DiscountService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductTest {
    @Test
    void CreateProductWithoutDiscount(){
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        DiscountService discountService = mock(DiscountService.class);
        CreatedProductService createdProductService = new CreatedProductService(produtoRepository, discountService);

        ProdutoDto produtoDto = new ProdutoDto(
                "Celular",
                6500.0,
                "Xiaomi Poco Phone 15 pro max",
               CategoryProductEnum.ELECTRONIC,
                19,
                false,
                0.0
        );

        ResponseEntity<?> response = createdProductService.create(produtoDto);
        ProdutoDtoResponse responseBody = (ProdutoDtoResponse) response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(6500.0, responseBody.infoDiscount().priceWithDiscount());
        verify(produtoRepository, times(1)).save(any(Produto.class));

        System.out.println("Test passed: CreateProductWithoutDiscount");
    }

    @Test
    void CreateProductWithDiscount(){
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        DiscountService discountService = mock(DiscountService.class);
        CreatedProductService createdProductService = new CreatedProductService(produtoRepository, discountService);

        when(discountService.applyDiscount(CategoryProductEnum.ELECTRONIC, 5000.0, 10.0))
                .thenReturn(4500.0);

        ProdutoDto produtoDto = new ProdutoDto(
                "Tv",
                5000.0,
                "Smart TV 50 polegadas",
                CategoryProductEnum.ELECTRONIC,
                20,
                true,
                10.0
        );

        ResponseEntity<?> response = createdProductService.create(produtoDto);
        ProdutoDtoResponse responseBody = (ProdutoDtoResponse) response.getBody();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(4500.0, responseBody.infoDiscount().priceWithDiscount());
        verify(produtoRepository, times(1)).save(any(Produto.class));

        System.out.println("Test passed: CreateProductWithDiscount");
    }
}
