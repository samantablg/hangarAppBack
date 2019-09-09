package com.myApp.controllers;

import com.myApp.order.builder.OrderBuilder;
import com.myApp.order.builder.OrderDtoBuilder;
import com.myApp.order.dto.OrderDto;
import com.myApp.order.model.Order;
import com.myApp.order.service.OrderService;
import com.myApp.product_order.service.Product_OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/commerce")
public class CommerceController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Product_OrderService product_orderService;

    @PostMapping("/test")
    public ResponseEntity<OrderDto> saveOrderTest(@RequestBody OrderDto orderDto) {
        Order order = new OrderBuilder(orderDto).getOrder();
        Order newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(
                new OrderDtoBuilder(order).getOrderDto(),
                HttpStatus.OK
        );
    }

}
