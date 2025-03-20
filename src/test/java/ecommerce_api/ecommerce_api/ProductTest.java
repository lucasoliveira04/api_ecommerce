package ecommerce_api.ecommerce_api;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.model.Produto;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import ecommerce_api.ecommerce_api.services.CreatedProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;

class ProductTest {
    @Test
    void CreateProduct(){
        ProdutoRepository produtoRepository = mock(ProdutoRepository.class);
        CreatedProductService createdProductService = new CreatedProductService(produtoRepository);

        ProdutoDto produtoDto = new ProdutoDto("Celular", 5000.0);

        ResponseEntity<?> response = createdProductService.create(produtoDto);

        assertEquals(200, response.getStatusCode().value());
        verify(produtoRepository, times(1)).save(any(Produto.class));

        System.out.println("The test passed with sucess");
    }
}
