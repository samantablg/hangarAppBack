package com.myApp.price.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myApp.price.dao.PriceDAO;
import com.myApp.price.exceptions.PriceException;
import com.myApp.price.model.Price;
import com.myApp.product.exceptions.ProductException;
import com.myApp.product.model.Product;
import com.myApp.product.service.ProductServiceImpl;

import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDAO priceDAO;

    @Autowired
    ProductServiceImpl productService;

    @Override
    public Price createEntryPrice(Product product, float price) {
        try {
            Price newEntry = new Price();
            newEntry.setPrice(price);
            newEntry.setProduct(product);
            Date date = new Date();
            newEntry.setDate(date);
            return priceDAO.createEntryPrice(newEntry);
        } catch (Exception e) {
            throw new ProductException.ProductExistException();
        }
    }

    @Override
    public List<Price> getAllPrices() {

        List<Price> prices = priceDAO.getAllPrices();
        if(prices != null)
            return prices;
        throw new PriceException.PriceNotFoundException();
    }

    @Override
    public Price createEntryPriceToProduct(long id, float price) {

        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            Price newPrice = new Price();
            newPrice.setPrice(price);
            newPrice.setProduct(product);
            return priceDAO.createEntryPrice(newPrice);
        }
        throw new PriceException.PriceNotFoundException();
    }

    @Override
    public List<Price> getAllPricesOfProduct(long id) {

        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            return priceDAO.getAllPricesOfProduct(product);
        }
        throw new ProductException.ProductExistException();
    }
}
