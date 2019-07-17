package com.myApp.price.service;

import com.myApp.price.model.Price;

import java.util.List;

public interface PriceService {

    List<Price> getAllPrices();

    Price createEntryPriceToProduct(long id, float price);

    List<Price> getAllPricesOfProduct(long id);

}
