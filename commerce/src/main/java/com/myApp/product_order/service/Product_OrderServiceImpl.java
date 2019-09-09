package com.myApp.product_order.service;

import com.myApp.product_order.dao.Product_OrderDao;
import com.myApp.product_order.model.Product_Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_OrderServiceImpl implements Product_OrderService {

    @Autowired
    private Product_OrderDao product_orderDao;

    @Override
    public Product_Order save(Product_Order product_order) {
        return product_orderDao.save(product_order);
    }
}
