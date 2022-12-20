package by.bsuir.entity;

public record DiscountCard(String cardName, double additionalDiscountPercent) {
    public double getAdditionalDiscount() {
        return additionalDiscountPercent / 100;
    }
}
