package com.myApp.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProductExtended extends Product {

    private double price;

    private List<Long> hangars = null;

}
