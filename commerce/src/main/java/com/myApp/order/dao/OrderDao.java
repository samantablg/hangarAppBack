package com.myApp.order.dao;

import com.myApp.model.UserProfile;
import com.myApp.order.model.Order;

import java.util.List;

public interface OrderDao {

    List<Order> getOrders();

    List<Order> getOrdersOfClient(UserProfile profile);

    Order saveOrder(Order order);

    Order getOrderById(long id);

    Order getOrderByIdAndProfile(long id, UserProfile profile);

    void deleteOrder(long id);

    boolean isOrderOfClient(UserProfile profile);

    boolean isOrderById(long id);

    boolean isOrderByIdAndProfile(long id, UserProfile profile);
}
