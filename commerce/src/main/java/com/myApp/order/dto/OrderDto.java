package com.myApp.order.dto;

import com.myApp.model.Product_Order;
import com.myApp.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class OrderDto {

    @NotNull
    private long id;

    @NotEmpty
    private List<Product_Order> products_orders = new ArrayList<Product_Order>();

    @NotEmpty
    private UserProfile profile;

    @Positive
    private float totalPrice;

    @Positive
    private long totalProducts;
}
