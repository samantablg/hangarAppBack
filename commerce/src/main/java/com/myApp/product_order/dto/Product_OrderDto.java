package com.myApp.product_order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class Product_OrderDto {

    @NotNull
    private long id;
    @NotNull
    private long hangar_id;
    @NotNull
    private long product_id;
    private long quantity;
}
