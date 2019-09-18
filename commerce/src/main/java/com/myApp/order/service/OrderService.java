package com.myApp.order.service;

import com.myApp.order.model.Order;
import com.myApp.product_order.model.Product_Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    List<Order> getOrdersOfClient(long id);

    Order saveOrder(Order order);

    Order updateOrder(long id);

    Order deleteProduct_Order(long id, Product_Order product_order);

    Order getOrderByIdAndProfile(long id, long profile);

    Order getOrderById(long id);

    boolean deleteOrder(long id);
}
