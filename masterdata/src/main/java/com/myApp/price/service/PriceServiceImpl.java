package com.myApp.price.service;

import com.myApp.price.dao.PriceDAO;
import com.myApp.price.exceptions.PriceException;
import com.myApp.price.model.Price;
import com.myApp.product.exceptions.ProductException;
import com.myApp.product.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    PriceDAO priceDAO;

    @Autowired
    ProductServiceImpl productService;

    @Override
    public List<Price> getAllPrices() {

        List<Price> prices = priceDAO.getAllPrices();
        if(prices != null)
            return prices;
        throw new PriceException.PriceNotFoundException();
    }

    @Override
    public Price createEntryPriceToProduct(long id, float price) {

        //TODO cambiar el código y la forma de meter la fecha
        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            Price newPrice = new Price();
            newPrice.setPrice(price);
            newPrice.setProduct(product);
            Date now = new Date();
            newPrice.setDate(now);
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
