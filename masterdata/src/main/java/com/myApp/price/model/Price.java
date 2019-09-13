package com.myApp.price.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myApp.model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "price")
@Getter @Setter
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column(name="date")
    private Date date;

    @Column(name="price")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Product product;
}