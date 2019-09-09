package com.myApp.order.model;

import com.myApp.product_order.model.Product_Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APP_ORDER")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

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

