package com.myApp.price.builder;

import com.myApp.price.dto.ProductExtended;
import com.myApp.model.Product;

public class ProductExtendedDtoBuilder {

    private ProductExtended productExtendedDto;

    public ProductExtendedDtoBuilder(Product product, float price ) {
        this.productExtendedDto = new ProductExtended();

        this.productExtendedDto.setId(product.getId());
        this.productExtendedDto.setName(product.getName());
        this.productExtendedDto.setDescription(product.getDescription());
        this.productExtendedDto.setState(product.isState());
        this.productExtendedDto.setPrice(price);
    }

    public ProductExtended getProductExtendedDto() {
        return productExtendedDto;
    }


}
