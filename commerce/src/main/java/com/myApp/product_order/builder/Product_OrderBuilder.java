package com.myApp.product_order.builder;

import com.myApp.product_order.dto.Product_OrderDto;
import com.myApp.product_order.model.Product_Order;

public class Product_OrderBuilder {

    private Product_Order product_order;

    public Product_OrderBuilder(Product_OrderDto product_orderDto) {
        this.product_order = new Product_Order();

        this.product_order.setId(product_orderDto.getId());
        this.product_order.setHangar_id(product_orderDto.getHangar_id());
        this.product_order.setProduct_id(product_orderDto.getProduct_id());
        this.product_order.setQuantity(product_orderDto.getQuantity());
    }

    public Product_Order getProduct_order() { return product_order; }
}
