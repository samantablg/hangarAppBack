package com.myApp.price.builder;

import com.myApp.price.dto.PriceDto;
import com.myApp.price.model.Price;

public class PriceBuilder {

    public Price price;

    public PriceBuilder(PriceDto priceDto) {
        this.price = new Price();

        this.price.setId(priceDto.getId());
        this.price.setDate(priceDto.getDate());
        this.price.setPrice(priceDto.getPrice());
        this.price.setProduct(priceDto.getProduct());
    }

    public Price getPrice() {
        return this.price;
    }
}
