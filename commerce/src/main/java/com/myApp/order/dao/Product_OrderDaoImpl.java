package com.myApp.order.dao;

import com.myApp.order.model.Product_Order;
import com.myApp.order.repository.Product_OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Product_OrderDaoImpl implements Product_OrderDao {

    @Autowired
    private Product_OrderRepository product_orderRepository;

    @Override
    public Product_Order saveProduct_Order(Product_Order product_order) {
        return product_orderRepository.save(product_order);
    }

    @Override
    public void deleteProduct_Order(Product_Order product_order) {
        product_orderRepository.delete(product_order);
    }

    @Override
    public Product_Order getProduct_Order(long id) {
        return product_orderRepository.getOne(id);
    }

    @Override
    public boolean isProduct_Order(long id) {
        return product_orderRepository.existsById(id);
    }
}
