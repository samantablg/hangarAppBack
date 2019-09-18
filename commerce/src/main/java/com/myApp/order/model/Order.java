package com.myApp.order.model;

import com.myApp.model.UserProfile;
import com.myApp.product_order.model.Product_Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "APP_ORDER")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @ManyToOne
    private UserProfile profile;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "products")
    private List<Product_Order> products_orders;

    /*@Column(name="date")
    private Date date;*/

    @Column(name = "total_price")
    private double total_price;

    @Column(name = "total_products")
    private long total_products;
}

