package ecommerce_api.ecommerce_api.services;

import ecommerce_api.ecommerce_api.dto.ProdutoDto;
import ecommerce_api.ecommerce_api.model.Produto;
import ecommerce_api.ecommerce_api.repository.ProdutoRepository;
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
            produto.setName(produtoDto.name());
            produto.setPrice(produtoDto.price());
            Produto data =  produtoRepository.save(produto);
            return ResponseEntity.ok().body(data);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
