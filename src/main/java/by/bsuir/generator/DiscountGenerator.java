package by.bsuir.util;

import by.bsuir.entity.Discount;
import by.bsuir.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiscountGenerator {

    private static final double MAX_DISCOUNT_PERCENT = 30.0;
    private static final double MIN_DISCOUNT_PERCENT = 0.0;
    private static final int MIN_QUANTITY_IN_ORDER = 5;

    private final Random random;

    public DiscountGenerator(Random random) {
        this.random = random;
    }

    public Discount nextDiscount() {
        double discountInPercent = random.nextDouble() * (MAX_DISCOUNT_PERCENT - MIN_DISCOUNT_PERCENT) + MIN_DISCOUNT_PERCENT;
        return new Discount(discountInPercent, MIN_QUANTITY_IN_ORDER);
    }
}
