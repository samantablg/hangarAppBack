package com.myApp.order.builder;

import com.myApp.order.dto.OrderDto;
import com.myApp.order.model.Order;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(OrderDto orderDto) {
        this.order = new Order();

        this.order.setId(orderDto.getId());
        this.order.setTotal_price(orderDto.getTotalPrice());
        this.order.setTotal_products(orderDto.getTotalProducts());;
        this.order.setProducts_orders(orderDto.getProducts_orders());
    }

    public Order getOrder() { return order; }
}
