package by.bsuir.util;

import by.bsuir.entity.Receipt;

import java.util.stream.DoubleStream;

public class ReceiptCalculator {

    public double getDiscount(Receipt receipt) {
    return 0;
    }


    public double getTotalPrice(Receipt receipt) {
        return receipt.getOrders().stream()
                .flatMapToDouble(e-> DoubleStream.of(e.getQuantity() * e.getProduct().getPrice()))
                .sum();
    }
}
