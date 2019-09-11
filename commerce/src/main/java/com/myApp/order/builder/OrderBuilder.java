package com.myApp.order.builder;

import com.myApp.model.Order;
import com.myApp.order.dto.OrderDto;

public class OrderBuilder {

    private Order order;

    public OrderBuilder(OrderDto orderDto) {
        this.order = new Order();

        this.order.setId(orderDto.getId());
        this.order.setProfile(orderDto.getProfile());
        this.order.setTotal_price(orderDto.getTotalPrice());
        this.order.setTotal_products(orderDto.getTotalProducts());;
        this.order.setProducts_orders(orderDto.getProducts_orders());
    }

    public Order getOrder() { return order; }
}
