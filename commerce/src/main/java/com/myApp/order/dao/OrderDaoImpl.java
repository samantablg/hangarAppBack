package com.myApp.order.dao;

import com.myApp.model.Order;
import com.myApp.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        try {
            orderRepository.saveAndFlush(order);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return order;
    }
}
