package by.bsuir.model;

public record PriceDiscountPair(double totalPrice, double priceWithDiscount) {
    public double getDiscount() {
        return totalPrice - priceWithDiscount;
    }
}
