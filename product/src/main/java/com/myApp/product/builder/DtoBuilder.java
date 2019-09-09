package com.myApp.product.builder;

import com.myApp.product.dto.ProductDto;
import com.myApp.model.Product;

public class DtoBuilder {

    private ProductDto productDto;

    public DtoBuilder(Product product) {
        this.productDto = new ProductDto();

        this.productDto.setId(product.getId());
        this.productDto.setName(product.getName());
        this.productDto.setDescription(product.getDescription());
        this.productDto.setState(product.isState());
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}
