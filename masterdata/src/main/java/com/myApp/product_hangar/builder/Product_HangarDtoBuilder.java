package com.myApp.product_hangar.builder;

import com.myApp.product_hangar.dto.Product_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;

public class Product_HangarDtoBuilder {

    private Product_HangarDto product_hangarDto;

    public Product_HangarDtoBuilder(Product_Hangar product_hangar) {
        this.product_hangarDto = new Product_HangarDto();

        this.product_hangarDto.setHangar(product_hangar.getHangar());
        this.product_hangarDto.setProduct(product_hangar.getProduct());
        this.product_hangarDto.setAmount(product_hangar.getAmount());
    }

    public Product_HangarDto getProduct_hangarDto() {
        return product_hangarDto;
    }
}
