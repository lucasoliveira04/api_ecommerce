package ecommerce_api.ecommerce_api;

import ecommerce_api.ecommerce_api.model.Product.ProductWithDiscountEntity;
import ecommerce_api.ecommerce_api.model.Product.Produto;
import ecommerce_api.ecommerce_api.repository.ProductWithDiscountRepository;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
import ecommerce_api.ecommerce_api.services.discount.service.ProductDiscountExpired;
import ecommerce_api.ecommerce_api.services.discount.service.ApplyDateInitAndEndDiscountProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

class ApplyDiscountProductTest {
    private ProductWithDiscountRepository productWithDiscountRepository;
    private ProdutoRepository produtoRepository;
    private ApplyDateInitAndEndDiscountProduct applyDiscountProduct;
    private ProductDiscountExpired productDiscountExpired;

    @BeforeEach
    void setUp(){
        productWithDiscountRepository = mock(ProductWithDiscountRepository.class);
        produtoRepository = mock(ProdutoRepository.class);
        applyDiscountProduct = new ApplyDateInitAndEndDiscountProduct(productWithDiscountRepository, produtoRepository);
        productDiscountExpired = new ProductDiscountExpired(productWithDiscountRepository);
    }

    @Test
    void shouldRemoveExpiredDiscounts() {
        Produto produto = new Produto();
        produto.setProductName("Test");
        produto.setHasDiscount(true);

        ProductWithDiscountEntity expiredDiscount = new ProductWithDiscountEntity();
        expiredDiscount.setProduto(produto);
        expiredDiscount.setEndDiscount(LocalDateTime.now().minusDays(1)); // Desconto expirado

        // Mockando o banco para retornar o produto expirado
        when(productWithDiscountRepository.findAll()).thenReturn(List.of(expiredDiscount));

        // Chamar o método que verifica e remove descontos expirados
        productDiscountExpired.checkDateInitialisationAndEndDiscount();

        // Verificar se o produto foi removido do banco
        verify(productWithDiscountRepository, times(1)).delete(expiredDiscount);

        // Verificar se o produto foi atualizado sem desconto e salvo novamente
        verify(productWithDiscountRepository, times(1)).save(expiredDiscount);
    }

    @Test
    void shouldNotRemoveValidDiscounts(){
        Produto produto = new Produto();
        produto.setProductName("Smart TV");
        produto.setHasDiscount(true);

        ProductWithDiscountEntity validDiscount = new ProductWithDiscountEntity();
        validDiscount.setProduto(produto);
        validDiscount.setEndDiscount(LocalDateTime.now().plusDays(10)); // Ainda valido

        // Mockando o banco para retornar o produto válido
        when(productWithDiscountRepository.findAll()).thenReturn(List.of(validDiscount));

        // Chamar o método que verifica descontos
        productDiscountExpired.checkDateInitialisationAndEndDiscount();

        verify(productWithDiscountRepository, never()).delete(any());
        verify(productWithDiscountRepository, never()).save(any());
    }
}
