package com.myApp.product.builder;

import com.myApp.product.dto.ProductDto;
import com.myApp.model.Product;

public class ProductBuilder {

    private Product product;

    public ProductBuilder(ProductDto hangarDto) {
        this.product = new Product();

        this.product.setId(hangarDto.getId());
        this.product.setName(hangarDto.getName());
        this.product.setDescription(hangarDto.getDescription());
        this.product.setState(hangarDto.isState());
    }

    public Product getProduct() {
        return product;
    }
}
