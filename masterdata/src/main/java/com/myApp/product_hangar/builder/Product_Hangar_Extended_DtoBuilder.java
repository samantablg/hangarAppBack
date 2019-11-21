package com.myApp.product_hangar.builder;

import com.myApp.product_hangar.dto.Product_Hangar_Extended_Dto;
import com.myApp.product_hangar.model.Product_Hangar;

public class Product_Hangar_Extended_DtoBuilder {

    private Product_Hangar_Extended_Dto product_hangar_extended_dto;

    public Product_Hangar_Extended_DtoBuilder(Product_Hangar product_hangar, String nameProduct) {
        this.product_hangar_extended_dto = new Product_Hangar_Extended_Dto();

        this.product_hangar_extended_dto.setHangar(product_hangar.getHangar());
        this.product_hangar_extended_dto.setProduct(product_hangar.getProduct());
        this.product_hangar_extended_dto.setAmount(product_hangar.getAmount());
        this.product_hangar_extended_dto.setNameProduct(nameProduct);
    }

    public Product_Hangar_Extended_Dto getProduct_hangar_extended_dto() {
        return product_hangar_extended_dto;
    }
}
