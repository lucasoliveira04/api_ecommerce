package ecommerce_api.ecommerce_api.dto;

import ecommerce_api.ecommerce_api.model.Produto;

public record ProdutoDtoResponse(Produto produto, InfoDiscount infoDiscount) {

    public ProdutoDtoResponse(Produto produto, double priceWithDiscount, double discount, boolean hasDiscount) {
        this(produto, new InfoDiscount(priceWithDiscount, discount, hasDiscount));
    }

    public record InfoDiscount(double priceWithDiscount, double discount, boolean hasDiscount) {}
}

