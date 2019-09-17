package com.myApp.order.dao;

import com.myApp.order.model.Order;
import com.myApp.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public boolean isOrderById(long id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public List<Order> getOrdersOfClient(long id) {
        return orderRepository.findAllByProfile(id);
    }

    @Override
    public boolean isOrderOfClient(long id) {
        return orderRepository.existsOrdersByProfile(id);
    }
}
