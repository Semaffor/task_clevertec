package by.bsuir.util;

import by.bsuir.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductGenerator {

    private static final double MAX_PRICE_VALUE = 30.0;
    private static final double MIN_PRICE_VALUE = 10.0;
    private static final int MAX_MENU_LENGTH = 30;

    private static Long productId = 1L;

    private final Random random;

    public ProductGenerator(Random random) {
        this.random = random;
    }

    public List<Product> nextProductList() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < MAX_MENU_LENGTH; i++) {
            products.add(nextProduct());
        }
        return products;
    }

    public Product nextProduct() {
        return Product.builder()
                .id(productId)
                .setDescription("Product_" + productId++)
                .setPrice(random.nextDouble() * (MAX_PRICE_VALUE - MIN_PRICE_VALUE) + MIN_PRICE_VALUE)
                .build();
    }
}
