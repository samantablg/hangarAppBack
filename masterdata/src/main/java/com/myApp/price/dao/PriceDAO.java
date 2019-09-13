package com.myApp.price.dao;

import com.myApp.price.model.Price;
import com.myApp.model.Product;

import java.util.List;

public interface PriceDAO {

    Price createEntryPrice(Price price);

    List<Price> getAllPrices();

    Price getLastPriceOfProduct(long product_id);

    List<Price> getAllPricesOfProduct(Product product);

    Boolean isProductWithPrice(long product_id);
}
