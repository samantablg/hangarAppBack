package com.myApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_ORDER")
@Getter @Setter
public class Product_Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    @Column(name="hangar_id")
    private long hangar_id;
    @Column(name="product_id")
    private long product_id;
    @Column(name="quantity")
    private long quantity;
}
