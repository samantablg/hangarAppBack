package com.myApp.order.dao;

import com.myApp.order.model.Order;
import com.myApp.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Boolean existOrderById(long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.getOne(id);
    }
}
