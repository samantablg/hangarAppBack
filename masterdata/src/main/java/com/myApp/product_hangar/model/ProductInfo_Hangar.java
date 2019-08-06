package com.myApp.product_hangar.model;

import com.myApp.product.model.Product;

public class ProductInfo_Hangar {

    private long hangar;
    private Product product;
    private long amount;

    public long getHangar() {
        return hangar;
    }

    public void setHangar(long hangar) {
        this.hangar = hangar;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
