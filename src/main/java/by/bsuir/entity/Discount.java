package by.bsuir.entity;

public class Discount {
    private double discountInPercent;
    private int minQuantity;

    public Discount(double discountInPercent, int minQuantity) {
        this.discountInPercent = discountInPercent;
        this.minQuantity = minQuantity;
    }

    public static Discount createDefault() {
        return new Discount(10, 5);
    }

    public double getDiscountInPercent() {
        return discountInPercent;
    }

    public double getDiscount() {
        return discountInPercent / 100;
    }

    public void setDiscountInPercent(double discountInPercent) {
        this.discountInPercent = discountInPercent;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }
}
