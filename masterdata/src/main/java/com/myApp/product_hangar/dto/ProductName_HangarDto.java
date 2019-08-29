package com.myApp.product_hangar.dto;

public class ProductName_HangarDto {

    private long hangar;
    private String nameProduct;
    private long amount;

    public long getHangar() {
        return hangar;
    }

    public void setHangar(long hangar) {
        this.hangar = hangar;
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
