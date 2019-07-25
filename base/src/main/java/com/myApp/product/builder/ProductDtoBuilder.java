package com.myApp.product.builder;

import com.myApp.product.dto.ProductDto;
import com.myApp.product.model.Product;

public class ProductDtoBuilder {

    private ProductDto productDto;

    public ProductDtoBuilder(Product product) {
        this.productDto = new ProductDto();

        this.productDto.setId(product.getId());
        this.productDto.setName(product.getName());
        this.productDto.setDescription(product.getDescription());
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}
