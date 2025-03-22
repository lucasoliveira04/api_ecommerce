package ecommerce_api.ecommerce_api.model.Product;

import ecommerce_api.ecommerce_api.dto.ProductWithDiscountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplyDateLimitDiscountRequest {
    private ProductWithDiscountDto productWithDiscountDto;
    private Long produtoId;
}
