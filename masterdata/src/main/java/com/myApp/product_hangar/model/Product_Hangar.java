package com.myApp.product_hangar.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_HANGAR")
@IdClass(Product_Hangar_PK.class)
public class Product_Hangar implements Serializable {

    private static final long serialVersionUID = -3064410667944568907L;

    @Id
    @Column(name = "hangar")
    //@NotEmpty
    private long hangar;

    @Id
    @Column(name = "product")
    //@NotEmpty
    private long product;

    @Column(name = "amount")
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
