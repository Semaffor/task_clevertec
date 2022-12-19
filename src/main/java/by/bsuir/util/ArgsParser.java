package by.bsuir.util;

import by.bsuir.entity.Order;
import by.bsuir.entity.Receipt;
import by.bsuir.service.MenuService;
import by.bsuir.service.impl.OrderServiceImpl;
import by.bsuir.service.ReceiptService;

import java.util.Date;

public class ArgsParser {

    private static final String INSIDE_ARG_DELIMITER = "-";
    private static Long productId = 1L;

    private final MenuService menuService;
    private final ReceiptService receiptService;
    private final OrderServiceImpl orderService;

    public ArgsParser(MenuService menuService, ReceiptService receiptService, OrderServiceImpl orderService) {
        this.menuService = menuService;
        this.receiptService = receiptService;
        this.orderService = orderService;
    }

    public Receipt parseArgs(String... args) {
        String cardName = null;
        for (String arg : args) {
            if (!arg.contains("card")) {
                orderService.addNewOrder(parsePairToOrder(arg));
            } else {
                cardName = parsePairToCardName(arg);
            }
        }

        return Receipt.builder()
                .cashierName("ME")
                .orders(orderService.getOrders())
                .cardName(cardName)
                .date(new Date())
                .build();
    }


    public String parsePairToCardName(String arg) {
        String[] splitedArg = arg.split(INSIDE_ARG_DELIMITER);
        return splitedArg[1];
    }

    //1-2
    public Order parsePairToOrder(String arg) {
        String[] splitedArg = arg.split(INSIDE_ARG_DELIMITER);
        Long menuProductId = Long.parseLong(splitedArg[0]);
        int quantity = Integer.parseInt(splitedArg[1]);

        return Order.builder()
                .id(menuProductId++)
                .product(menuService.findById(menuProductId))
                .quantity(quantity)
                .build();
    }

}
