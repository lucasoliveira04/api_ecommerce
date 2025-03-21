package ecommerce_api.ecommerce_api.dto;

import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;

public record ProdutoDto(
        String name,
        Double price,
        String description,
        CategoryProductEnum categoryProduct,
        Integer productQuantity,
        Boolean hasDiscount,
        Double discount
)
{}