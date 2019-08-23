package com.myApp.price.builder;

import com.myApp.price.dto.PriceDto;
import com.myApp.price.model.Price;

import java.io.Serializable;

public class PriceDtoBuilder implements Serializable {

    private PriceDto priceDto;

    public PriceDtoBuilder(Price price) {
        this.priceDto = new PriceDto();

        this.priceDto.setId(price.getId());
        this.priceDto.setDate(price.getDate());
        this.priceDto.setPrice(price.getPrice());
        this.priceDto.setProduct(price.getProduct());
    }

    public PriceDto getPriceDto() {
        return this.priceDto;
    }
}
