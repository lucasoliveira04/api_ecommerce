package ecommerce_api.ecommerce_api.services.Discount;

public class DiscountAutomobile implements IDiscount{

    private Double discount;

    public DiscountAutomobile(Double discount) {
        this.discount = Math.max(0, Math.min(discount, 25));
    }

    @Override
    public double applyDiscount(double price) {
        return price - (price * (this.discount / 100));
    }
}
