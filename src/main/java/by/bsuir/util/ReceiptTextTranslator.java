package by.bsuir.util;

import by.bsuir.entity.Order;
import by.bsuir.entity.Product;
import by.bsuir.entity.Receipt;
import by.bsuir.util.DateHandler;
import by.bsuir.util.ReceiptCalculator;

public class ReceiptTextTranslator {

    public static final String DELIMITER = "-";
    public static final int DELIMITER_LINE_LENGTH = 30;

    private final DateHandler dateHandler;
    private final ReceiptCalculator calculator;

    public ReceiptTextTranslator(DateHandler dateHandler, ReceiptCalculator receiptCalculator) {
        this.dateHandler = dateHandler;
        this.calculator = receiptCalculator;
    }

    public String convertToText(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        String date = dateHandler.getSimpleDate(receipt.getDate());
        String time = dateHandler.getTime(receipt.getDate());

        sb.append(String.format("CASHIER: %7s\t\t DATE: %s\n", receipt.getCashierName(), date));
        sb.append(String.format("%16s \t\t TIME: %s\n","", time));
        sb.append(generateLine(DELIMITER_LINE_LENGTH));
        sb.append("QTY\t  DESCRIPTION\t\t\tPRICE\tTOTAL\n");
        for (Order order : receipt.getOrders()) {
            Product product = order.getProduct();
            sb.append(order.getId()).append("\t\t")
                    .append(product.getDescription()).append("\t\t\t")
                    .append(String.format("%.2f", product.getPrice())).append("\t")
                    .append(String.format("%.2f", product.getPrice() * order.getQuantity())).append("\n");
        }
        sb.append(generateLine(DELIMITER_LINE_LENGTH));
        sb.append(String.format("Discount:\t\t\t%.2f\n", calculator.getDiscount(receipt)));
        sb.append(String.format("Total: %.2f\n", calculator.getTotalPrice(receipt)));
        return sb.toString();
    }

    private String generateLine(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(DELIMITER);
        }
        return sb.append("\n").toString();
    }
}
