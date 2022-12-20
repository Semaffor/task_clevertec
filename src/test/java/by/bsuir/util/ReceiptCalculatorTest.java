package by.bsuir.util;

import by.bsuir.entity.*;
import by.bsuir.model.PriceDiscountPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
class ReceiptCalculatorTest {

    private static final double DELTA = 0.001;

    private static ReceiptCalculator receiptCalculator = new ReceiptCalculator();

    private static Order order;
    private static Receipt receipt;

    @BeforeEach
    public void init() {
        Discount discount = new Discount(1.0, 5);
        Product product = Product.builder()
                .setPrice(10.0)
                .discount(discount)
                .build();
        order = Order.builder()
                .id(1L)
                .quantity(10)
                .product(product)
                .build();
        receipt = new Receipt();
        receipt.setOrders(List.of(order, order));
    }

    @Test
    void testGetTotalPriceAndDiscountWhenCalculationTrue() {
        PriceDiscountPair result = receiptCalculator.getTotalPriceAndDiscount(order);
        PriceDiscountPair expected = new PriceDiscountPair(100.00, 99.0);
        assertEquals(expected, result);
    }

    @Test
    void testGetTotalPriceAndDiscountWhenCalculationFalse() {
        PriceDiscountPair result = receiptCalculator.getTotalPriceAndDiscount(order);
        PriceDiscountPair expected = new PriceDiscountPair(10.00, 9.0);
        assertNotEquals(expected, result);
    }

    @Test
    void testGetTotalDiscountShouldPassWhenCorrect() {
        double result = receiptCalculator.getTotalDiscount(receipt);
        assertEquals(2.0, result, DELTA);
    }

    @Test
    void testGetTotalPriceWithDiscount() {
        double result = receiptCalculator.getTotalPriceWithDiscount(receipt);
        assertEquals(198.0, result, DELTA);
    }

    @Test
    void testGetTotalPriceWhenReceiptEmptyShouldPass() {
        double result = receiptCalculator.getTotalPrice(receipt);
        assertEquals(200.00, result, DELTA);
    }

    @Test
    void testGetTotalPriceWhenReceiptEmptyShouldNotPass() {
        double result = receiptCalculator.getTotalPrice(receipt);
        assertEquals(100.00, result, DELTA);
    }

    @Test
    void testGetTotalPriceWithCardWhenCorrectShouldPass() {
        DiscountCard discountCard = new DiscountCard("1234", 1);
        double result = receiptCalculator.getTotalPriceWithCard(discountCard, 100.00);
        assertEquals(99.0, result);
    }

    @Test
    void testGetTotalPriceWithCardWhenIncorrectShouldNotEquals() {
        DiscountCard discountCard = new DiscountCard("1234", 10);
        double result = receiptCalculator.getTotalPriceWithCard(discountCard, 100.00);
        assertNotEquals(99.0, result);
    }
}