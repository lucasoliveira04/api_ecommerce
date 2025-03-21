package ecommerce_api.ecommerce_api.dto;

public record ProdutoDto(
        String name,
        Double price,
        String description,
        String categoryProduct,
        Integer productQuantity
)
{}