package ecommerce_api.ecommerce_api.services.Discount;

import ecommerce_api.ecommerce_api.enums.CategoryProductEnum;

public class DiscountService {
    public static double applyDiscount(CategoryProductEnum categoryProductEnum, double price, double discount) {
        IDiscount discountStrategy;

        switch (categoryProductEnum){
            case AUTOMOBILE -> discountStrategy = new DiscountAutomobile(discount);
            case ELECTRONIC -> discountStrategy = new DiscountElectronic(discount);
            case FOOD -> discountStrategy = new DiscountFood(discount);
            default -> discountStrategy = null;
        }

        return discountStrategy.applyDiscount(price);
    }
}
