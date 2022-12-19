package by.bsuir.service;

import by.bsuir.entity.Order;
import by.bsuir.entity.Product;

import java.util.List;

public interface OrderService {

    void addNewOrder(Order order);
    List<Order> getOrders();
}
