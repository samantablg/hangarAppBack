package com.myApp.price.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.myApp.price.model.Price;
import com.myApp.price.repository.PriceRepository;
import com.myApp.model.Product;

import java.util.List;

@Component
public class PriceDAOImpl implements PriceDAO {

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public Price createEntryPrice(Price price) { return priceRepository.save(price); }

    @Override
    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }
    
    @Override
    public Price getLastPriceOfProduct(long product_id) {
        return priceRepository.getLastPriceOfProduct(product_id);
    }

    @Override
    public List<Price> getAllPricesOfProduct(Product product) {
        return priceRepository.findPricesByProduct(product);
    }

    @Override
    public boolean isProductWithPrice(long product_id) {
        return priceRepository.existsPriceByProduct(product_id);
    }

}
