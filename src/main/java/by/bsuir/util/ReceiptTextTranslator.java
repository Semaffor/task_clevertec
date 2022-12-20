package by.bsuir.util;

import by.bsuir.entity.Order;
import by.bsuir.entity.Product;
import by.bsuir.entity.Receipt;
import by.bsuir.model.PriceDiscountPair;

public class ReceiptTextTranslator {

    public static final String DELIMITER = "-";
    public static final int DELIMITER_LINE_LENGTH = 40;

    private final DateHandler dateHandler;
    private final ReceiptCalculator calculator;

    public ReceiptTextTranslator(DateHandler dateHandler, ReceiptCalculator receiptCalculator) {
        this.dateHandler = dateHandler;
        this.calculator = receiptCalculator;
    }

    public String convertInText(Receipt receipt) {
        StringBuilder sb = new StringBuilder();

        getReceiptHead(receipt, sb);
        sb.append("QTY\t  DESCRIPTION\t\t\tPRICE\tTOTAL\n");
        for (Order order : receipt.getOrders()) {
            getLineWithOrder(order, sb);
        }
        getReceiptFoot(receipt, sb);

        return sb.toString();
    }

    private void getLineWithOrder(Order order, StringBuilder sb) {
        Product product = order.getProduct();
        PriceDiscountPair priceAndDiscount = calculator.getTotalPriceAndDiscount(order);
        sb.append(order.getQuantity()).append("\t\t")
                .append(product.getDescription()).append("\t\t\t")
                .append(String.format("$%.2f", product.getPrice())).append("\t")
                .append(String.format("$%.2f", priceAndDiscount.totalPrice())).append("\n");
        double discount = priceAndDiscount.getDiscount();
        if (discount > 0.0) {
            sb.append(String.format("Скидка (%.2f%c):\t\t\t\t\t $%.2f", discount, '%',
                    order.getProduct().getDiscount().getDiscountInPercent())).append("\n");
        }
    }

    private void getReceiptHead(Receipt receipt, StringBuilder sb) {
        String date = dateHandler.getSimpleDate(receipt.getDate());
        String time = dateHandler.getTime(receipt.getDate());
        sb.append(String.format("CASHIER: %7s\t\t DATE: %s\n", receipt.getCashierName(), date));
        sb.append(String.format("%16s \t\t TIME: %s\n", "", time));
        sb.append(generateLine(DELIMITER_LINE_LENGTH));
    }

    private void getReceiptFoot(Receipt receipt, StringBuilder sb) {
        sb.append(generateLine(DELIMITER_LINE_LENGTH));
        sb.append(String.format("Discount:\t\t\t\t\t\t\t$%.2f\n", calculator.getTotalDiscount(receipt)));
        sb.append(String.format("Total:\t\t\t\t\t\t\t\t$%.2f\n", calculator.getTotalPriceWithDiscount(receipt)));
    }

    private String generateLine(int length) {
        return DELIMITER.repeat(Math.max(0, length)) + "\n";
    }
}
