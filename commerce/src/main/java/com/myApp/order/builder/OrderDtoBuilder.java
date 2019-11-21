package com.myApp.order.builder;

import com.myApp.order.dto.OrderDto;
import com.myApp.order.model.Order;

public class OrderDtoBuilder {

    private OrderDto orderDto;

    public OrderDtoBuilder(Order order) {
        this.orderDto = new OrderDto();

        this.orderDto.setId(order.getId());
        // this.orderDto.setProfile(order.getProfile());
        this.orderDto.setTotalPrice(order.getTotal_price());
        this.orderDto.setTotalProducts(order.getTotal_products());
        this.orderDto.setProductsOfOrder(order.getProducts_orders());
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }
}
