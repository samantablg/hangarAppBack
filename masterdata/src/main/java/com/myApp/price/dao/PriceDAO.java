package com.myApp.price.dao;

import com.myApp.price.model.Price;
import com.myApp.model.Product;

import java.util.List;

public interface PriceDAO {

    Price createEntryPrice(Price price);

    List<Price> getAllPrices();

    Price getLastPriceOfProduct(long id);

    List<Price> getAllPricesOfProduct(Product product);
}
