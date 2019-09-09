package com.myApp.price.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myApp.model.Product;

import java.util.Date;

public class PriceDto {

    private long id;
    private Date date;
    private float price;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
