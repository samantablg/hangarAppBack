package com.myApp.product_order.dao;

import com.myApp.model.Product_Order;
import com.myApp.product_order.repository.Product_OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Product_OrderDaoImpl implements Product_OrderDao {

    @Autowired
    private Product_OrderRepository product_orderRepository;

    @Override
    public Product_Order save(Product_Order product_order) {

        try {
            return product_orderRepository.save(product_order);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return product_order;
        }
    }
}
