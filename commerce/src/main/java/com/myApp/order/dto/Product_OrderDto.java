package com.myApp.order.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
