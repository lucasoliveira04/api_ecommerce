package ecommerce_api.ecommerce_api.dto;

import ecommerce_api.ecommerce_api.model.Produto;

import java.time.LocalDateTime;

public record ProductWithDiscountDto(Produto produto, LocalDateTime initDiscount, LocalDateTime endDiscount) {}
