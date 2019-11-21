package com.myApp.order.service;

import com.myApp.order.model.Order;
import com.myApp.order.model.Product_Order;

import java.util.List;

public interface OrderService {

    List<Order> getOrders();

    List<Order> getOrdersOfClient(long id);

    Order saveOrder(Order order, long id_client);

    Order deleteProduct_Order(long id_order, long id_product_order);

    Order addProductToOrder(long id_order, Product_Order product_order);

    Order updateProductOfOrder(long id_order, long id_product_order, long quantity);

    Order getOrderByIdAndProfile(long id, long profile);

    Order getOrderById(long id);

    boolean deleteOrder(long id);
}
