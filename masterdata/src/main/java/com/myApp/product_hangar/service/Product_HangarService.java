package com.myApp.product_hangar.service;

import com.myApp.model.Product;
import com.myApp.product_hangar.dto.ProductName_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;

import java.util.List;

public interface Product_HangarService {

    Product_Hangar associateProductToHangar(Product_Hangar product_hangar);

    List<Product_Hangar> getAll();

    List<Product_Hangar> getProductsOfHangar(long id);

    List<ProductName_HangarDto> getNameOfProductsOfHangar(long id);

    List<Product_Hangar> getHangarsOfProduct(long id);

    Product_Hangar updateAmount(long product, long hangar, long amount);

    boolean unlinkProductOfHangar(long product, long hangar);

    boolean isProductLinkToHangar(long idProduct);

    List<Product> getProductsUnlinkOfHangar(long idHangar);

    Product_Hangar updateAmountAfterOrder(long product, long hangar, long amount);

}
