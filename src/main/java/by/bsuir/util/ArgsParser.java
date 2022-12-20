package by.bsuir.util;

import by.bsuir.entity.DiscountCard;
import by.bsuir.entity.Order;
import by.bsuir.entity.Receipt;
import by.bsuir.service.CardService;
import by.bsuir.service.MenuService;
import by.bsuir.service.OrderService;

import java.util.Date;

public class ArgsParser {

    private static final String INSIDE_ARG_DELIMITER = "-";
    private static Long productId = 1L;

    private final MenuService menuService;
    private final CardService cardService;
    private final OrderService orderService;

    public ArgsParser(MenuService menuService, CardService cardService, OrderService orderService) {
        this.menuService = menuService;
        this.cardService = cardService;
        this.orderService = orderService;
    }

    public Receipt parseReceipt(String... args) {
        DiscountCard discountCard = null;
        for (String arg : args) {
            if (!arg.contains("card")) {
                orderService.addNewOrder(parsePairToOrder(arg));
            } else {
                discountCard = parsePairToDiscountCard(arg);
            }
        }

        return Receipt.builder()
                .cashierName("ME")
                .orders(orderService.getOrders())
                .discountCard(discountCard)
                .date(new Date())
                .build();
    }


    public DiscountCard parsePairToDiscountCard(String arg) {
        String[] splitedArg = arg.split(INSIDE_ARG_DELIMITER);
        String cardName = splitedArg[1];
        return cardService.findByCardName(cardName);
    }

    public Order parsePairToOrder(String arg) {
        String[] splitedArg = arg.split(INSIDE_ARG_DELIMITER);
        Long menuProductId = Long.parseLong(splitedArg[0]);
        int quantity = Integer.parseInt(splitedArg[1]);

        return Order.builder()
                .id(menuProductId)
                .product(menuService.findById(menuProductId++))
                .quantity(quantity)
                .build();
    }

}
