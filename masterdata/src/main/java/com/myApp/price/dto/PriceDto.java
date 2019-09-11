package com.myApp.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myApp.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter @Setter
public class PriceDto {

    private long id;
    private Date date;
    @NotNull
    private float price;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @NotNull
    private Product product;
}
