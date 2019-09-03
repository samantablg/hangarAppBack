package com.myApp.product_hangar.dao;

import com.myApp.product_hangar.model.Product_Hangar;

import java.util.List;

public interface Product_HangarDAO {

    Product_Hangar addProductToHangar(Product_Hangar product_hangar);

    Product_Hangar getRelationship(long product, long hangar);

    List<Product_Hangar> getAll();

    List<Product_Hangar> getProductsOfHangar(long hangar);

    List<Product_Hangar> getHangarsOfProduct(long product);

    Product_Hangar updateAmount(Product_Hangar update);

    Boolean deleteRelationship(Product_Hangar product_hangar);

    Boolean isProductLinkToHangar(long idProduct);

}
