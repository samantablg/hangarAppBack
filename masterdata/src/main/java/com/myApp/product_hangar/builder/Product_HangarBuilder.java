package com.myApp.product_hangar.builder;

import com.myApp.product_hangar.dto.Product_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;

public class Product_HangarBuilder {

    private Product_Hangar product_hangar;

    public Product_HangarBuilder(Product_HangarDto product_hangarDto) {
        this.product_hangar = new Product_Hangar();

        this.product_hangar.setHangar(product_hangarDto.getHangar());
        this.product_hangar.setProduct(product_hangarDto.getProduct());
        this.product_hangar.setAmount(product_hangarDto.getAmount());
    }

    public Product_Hangar getProduct_hangar() {
        return this.product_hangar;
    }
}
