package by.bsuir.util;

import by.bsuir.entity.Discount;
import by.bsuir.entity.Order;
import by.bsuir.entity.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ReceiptCalculatorTest {

    @Mock
    private ReceiptCalculator receiptCalculator;

    @Test
    void getTotalPriceAndDiscount() {
//        menu = List.of(
//                Product.builder()
//                        .id(1L)
//                        .discount(new Discount(5, 5))
//                        .setDescription("Product_" + 1)
//                        .setPrice(10.0)
//                        .build()
//        );
//        Order order = Mockito.mock(Order.class);
//        order.setId(1L);
//        order.setQuantity(10);
//        order.setProduct(Mockito.mock(Product.class));
//        Mockito.when(receiptCalculator.)
//        receiptCalculator.getTotalPriceAndDiscount(order);
    }

    @Test
    void getTotalDiscount() {
    }

    @Test
    void getTotalPriceWithDiscount() {
    }

    @Test
    void getTotalPrice() {
    }

    @Test
    void getTotalPriceWithCard() {
    }
}