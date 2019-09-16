package com.myApp.product_hangar.dao;

import com.myApp.product_hangar.model.Product_Hangar;

import java.util.List;

public interface Product_HangarDAO {

    List<Product_Hangar> getAll();

    List<Product_Hangar> getProductsOfHangar(long hangar);

    List<Product_Hangar> getHangarsOfProduct(long product);

    List<Product_Hangar> getAllNotContentInHangar(long hangar);

    Product_Hangar addProductToHangar(Product_Hangar product_hangar);

    Product_Hangar getRelationship(long product, long hangar);

    Product_Hangar updateAmount(Product_Hangar update);

    void deleteRelationship(Product_Hangar product_hangar);

    Boolean isProductLinkToAnyHangar(long product);

    Boolean isProductLinkToHangar(long product, long hangar);

}
