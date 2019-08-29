package com.myApp.product_hangar.builder;

import com.myApp.product_hangar.dto.ProductName_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;

public class ProductName_HangarDtoBuilder {

    private ProductName_HangarDto productName_hangarDto;

    public ProductName_HangarDtoBuilder(Product_Hangar product_hangar, String nameProduct) {
        this.productName_hangarDto = new ProductName_HangarDto();

        this.productName_hangarDto.setHangar(product_hangar.getHangar());
        this.productName_hangarDto.setAmount(product_hangar.getAmount());
        this.productName_hangarDto.setNameProduct(nameProduct);
    }

    public ProductName_HangarDto getProductName_hangarDto_hangarDto() {
        return productName_hangarDto;
    }
}
