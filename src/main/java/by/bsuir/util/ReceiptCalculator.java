package by.bsuir.util;

import by.bsuir.entity.Discount;
import by.bsuir.entity.DiscountCard;
import by.bsuir.entity.Order;
import by.bsuir.entity.Receipt;
import by.bsuir.model.PriceDiscountPair;

import java.util.stream.DoubleStream;

public class ReceiptCalculator {

    public PriceDiscountPair getTotalPriceAndDiscount(Order order) {
        return new PriceDiscountPair(order.getTotalPrice(), getOrderPriceWithDiscount(order));
    }

    public double getTotalDiscount(Receipt receipt) {
        return getTotalPrice(receipt) - getTotalPriceWithDiscount(receipt);
    }

    public double getTotalPriceWithDiscount(Receipt receipt) {
        double priceWithItemDiscount = receipt.getOrders().stream()
                .flatMapToDouble(order -> DoubleStream.of(getOrderPriceWithDiscount(order)))
                .sum();

        DiscountCard card = receipt.getCard();
        if (card != null && card.additionalDiscountPercent() > 0.0) {
            return priceWithItemDiscount * (1 - card.getAdditionalDiscount());
        }
        return priceWithItemDiscount;
    }

    public double getTotalPrice(Receipt receipt) {
        return receipt.getOrders().stream()
                .flatMapToDouble(order -> DoubleStream.of(order.getQuantity() * order.getProduct().getPrice()))
                .sum();
    }

    public double getTotalPriceWithCard(DiscountCard card, double currentPrice) {
        return (1 - card.getAdditionalDiscount()) * currentPrice;
    }

    private double getOrderPriceWithDiscount(Order order) {
        Discount orderDiscount = order.getProduct().getDiscount();
        if (orderDiscount == null || orderDiscount.getMinQuantity() > order.getQuantity()) {
            return order.getProduct().getPrice() * order.getQuantity();
        }
        return (1 - orderDiscount.getDiscount()) * order.getTotalPrice();
    }

}
