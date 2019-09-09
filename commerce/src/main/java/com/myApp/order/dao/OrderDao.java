package com.myApp.order.dao;

import com.myApp.order.model.Order;

public interface OrderDao {

    Order saveOrder(Order order);
}
