package by.bsuir.service.impl;

import by.bsuir.entity.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl {

    private final List<Order> orders = new ArrayList<>();

    public void addNewOrder(Order order) {
        Optional<Order> similarObjectOptional = orders.stream()
                .filter(listOrder -> listOrder.equals(order))
                .findFirst();

        if (similarObjectOptional.isPresent()) {
            Order similarOrder = similarObjectOptional.get();
            similarOrder.setQuantity(similarOrder.getQuantity() + order.getQuantity());
        } else {
            orders.add(order);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
