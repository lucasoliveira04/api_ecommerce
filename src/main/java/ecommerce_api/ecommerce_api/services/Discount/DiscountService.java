package ecommerce_api.ecommerce_api.services.Discount;

import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {
    public double applyDiscount(CategoryProductEnum categoryProductEnum, double price, double discount) {
        IDiscount discountStrategy = switch (categoryProductEnum){
            case AUTOMOBILE ->  new DiscountAutomobile(discount);
            case ELECTRONIC ->  new DiscountElectronic(discount);
            case FOOD ->    new DiscountFood(discount);
            default -> (p) -> p;
        };

        return discountStrategy.applyDiscount(price);
    }
}
