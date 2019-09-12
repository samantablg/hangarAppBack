package com.myApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "APP_ORDER")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    private UserProfile profile;

    @OneToMany
    @Column(name="products")
    private List<Product_Order> products_orders;

    /*@Column(name="date")
    private Date date;*/

    @Column(name="total_price")
    private float total_price;

    @Column(name="total_products")
    private long total_products;

}

