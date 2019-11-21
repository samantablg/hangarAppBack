package com.myApp.product_hangar.dto;

public class Product_Hangar_Extended_Dto {

    private long hangar;
    private long product;
    private String nameProduct;
    private long amount;

    public long getHangar() {
        return hangar;
    }

    public void setHangar(long hangar) {
        this.hangar = hangar;
    }

    public long getProduct() {
        return product;
    }

    public void setProduct(long product) {
        this.product = product;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
