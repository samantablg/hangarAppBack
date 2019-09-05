package com.myApp.product.builder;

import com.myApp.product.dto.ProductExtendedDto;
import com.myApp.product.model.Product;

public class ProductExtendedDtoBuilder {

    private ProductExtendedDto productExtendedDto;

    public ProductExtendedDtoBuilder(Product product, float price ) {
        this.productExtendedDto = new ProductExtendedDto();
        this.productExtendedDto.setId(product.getId());
        this.productExtendedDto.setName(product.getName());
        this.productExtendedDto.setDescription(product.getDescription());
        this.productExtendedDto.setState(product.isState());
        this.productExtendedDto.setPrice(price);

    }

    public ProductExtendedDto getProductExtendedDto() {
        return productExtendedDto;
    }


}
