package com.myApp.product_hangar.dao;

import com.myApp.product_hangar.model.Product_Hangar;

import java.util.List;

public interface Product_HangarDAO {

    List<Product_Hangar> getAll();

    List<Product_Hangar> getProductsOfHangar(long hangar);

    List<Product_Hangar> getHangarsOfProduct(long product);

    List<Product_Hangar> getAllNotContentInHangar(long hangar);

    Product_Hangar addProductToHangar(Product_Hangar product_hangar);

    Product_Hangar getRelationship(long hangar, long product);

    Product_Hangar updateAmount(Product_Hangar update);

    void deleteRelationship(Product_Hangar product_hangar);

    boolean isProductLinkToAnyHangar(long product);

    boolean isProductLinkToHangar(long hangar, long product);

    boolean isHangarNotEmpty(long hangar);

}
