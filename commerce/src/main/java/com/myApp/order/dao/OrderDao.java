package com.myApp.order.dao;

import com.myApp.order.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrdersOfClient(long id);

    Order saveOrder(Order order);

    Order getOrderById(long id);

    void deleteOrder(long id);

    boolean isOrderOfClient(long id);

    boolean isOrderById(long id);
}
