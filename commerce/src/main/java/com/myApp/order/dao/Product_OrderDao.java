package com.myApp.order.dao;

import com.myApp.order.model.Product_Order;

public interface Product_OrderDao {

    Product_Order saveProduct_Order(Product_Order product_order);

    void deleteProduct_Order(Product_Order product_order);

    Product_Order getProduct_Order(long id);

    boolean isProduct_Order(long id);
}
