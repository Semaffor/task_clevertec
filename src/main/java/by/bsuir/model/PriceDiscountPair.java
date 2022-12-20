package by.bsuir.model;

public record PriceDiscountPair(double totalPrice, double priceWithDiscount) {
    public double getItemDiscountInMoney() {
        return totalPrice - priceWithDiscount;
    }
}
