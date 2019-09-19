package com.myApp.order.service;

import com.myApp.order.model.Product_Order;

public interface Product_OrderService {

    double getTotalPriceOfProductOrdered(Product_Order product_order);

    void deleteProduct_Order(Product_Order product_order);

    Product_Order saveProduct_Order(Product_Order product_order);

    Product_Order getProduct_order(long id_product_order);

    boolean isProduct_Order(long id_product_order);

}
