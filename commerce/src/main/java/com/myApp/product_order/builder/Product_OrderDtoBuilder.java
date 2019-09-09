package com.myApp.product_order.builder;

import com.myApp.product_order.dto.Product_OrderDto;
import com.myApp.product_order.model.Product_Order;

public class Product_OrderDtoBuilder {

    private Product_OrderDto product_orderDto;

    public Product_OrderDtoBuilder(Product_Order product_order) {
        this.product_orderDto = new Product_OrderDto();

        this.product_orderDto.setId(product_order.getId());
        this.product_orderDto.setHangar_id(product_order.getHangar_id());
        this.product_orderDto.setProduct_id(product_order.getProduct_id());
        this.product_orderDto.setQuantity(product_order.getQuantity());
    }

    public Product_OrderDto getProduct_orderDto() { return product_orderDto; }
}
