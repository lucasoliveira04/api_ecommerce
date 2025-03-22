package ecommerce_api.ecommerce_api.dto;

import ecommerce_api.ecommerce_api.model.Product.Produto;

import java.time.LocalDateTime;

public record ProductWithDiscountDto(Produto produtoId, LocalDateTime initDiscount, LocalDateTime endDiscount) {}
