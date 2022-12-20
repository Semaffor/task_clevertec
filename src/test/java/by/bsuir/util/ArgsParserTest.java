package by.bsuir.util;

import by.bsuir.entity.DiscountCard;
import by.bsuir.entity.Order;
import by.bsuir.entity.Product;
import by.bsuir.entity.Receipt;
import by.bsuir.service.CardService;
import by.bsuir.service.MenuService;
import by.bsuir.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ArgsParserTest {

    @Mock
    MenuService menuService;

    @Mock
    CardService cardService;

    @Mock
    OrderService orderService;

    @InjectMocks
    private ArgsParser argsParser;

    private AutoCloseable closeable;

    @BeforeEach
    void initService() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    void testParseArgsWhenCorrectShouldPass() {
        String[] testArgs = {"1-2", "3-4", "5-6", "3-1", "2-1"};
        doNothing().when(orderService).addNewOrder(any());
        when(orderService.getOrders()).thenReturn(null);
        Receipt result = argsParser.parseReceipt(testArgs);

        assertEquals(result.getCashierName(), "ME");
        assertNotNull(result.getDate());
    }


    @Test
    void parsePairToDiscountCard() {
        String testCardString = "card-123";
        DiscountCard expected = new DiscountCard("123", 1);
        when(cardService.findByCardName("123")).thenReturn(expected);

        DiscountCard result = argsParser.parsePairToDiscountCard(testCardString);
        assertEquals(expected, result);
    }

    @Test
    void parsePairToOrder() {
        String testData = "123-5";
        Product product = Product.builder().id(123L).build();
        when(menuService.findById(123L)).thenReturn(product);
        Order result = argsParser.parsePairToOrder(testData);
        Order expected = Order.builder().id(123L).quantity(5).build();

        assertEquals(expected.getId(), result.getId());
        assertEquals(expected.getQuantity(), result.getQuantity());
    }
}