package com.myApp.order.dao;

import com.myApp.model.UserProfile;
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
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersOfClient(UserProfile profile) {
        return orderRepository.findAllByProfile(profile);
    }

    @Override
    public Order saveOrder(Order order) { return orderRepository.save(order); }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.getOne(id);
    }

    @Override
    public Order getOrderByIdAndProfile(long id, UserProfile profile) {
        return orderRepository.findOrderByIdAndProfile(id, profile);
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
    public boolean isOrderOfClient(UserProfile profile) {
        return orderRepository.existsByProfile(profile);
    }

    @Override
    public boolean isOrderByIdAndProfile(long id, UserProfile profile) {
        return orderRepository.existsByIdAndProfile(id, profile);
    }
}
