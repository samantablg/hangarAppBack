package com.myApp.price.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.exception.GeneralException;
import com.myApp.price.dao.PriceDAO;
import com.myApp.price.model.Price;
import com.myApp.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDAO priceDAO;

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public List<Price> getAllPrices() {
        List<Price> prices = priceDAO.getAllPrices();
        if(!prices.isEmpty())
            return prices;
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public Price createEntryPriceToProduct(long id, double price) {

        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            Price _price = shapePrice(product, price);
            return priceDAO.createEntryPrice(_price);
        }
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<Price> getAllPricesOfProduct(long id) {

        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            return priceDAO.getAllPricesOfProduct(product);
        }
        throw new  ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    @Override
    public Price getCurrentPriceOfProduct(long id) {
        if(productService.existProduct(id))
            return priceDAO.getLastPriceOfProduct(id);
        throw new ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    private Price shapePrice(Product product, double price) {
        Price _price = new Price();
        _price.setProduct(product);
        _price.setPrice(price);
        _price.setDate(new Date());
        return _price;
    }

    private void isCurrentPrice(Price price) {
        Price _price = priceDAO.getLastPriceOfProduct(price.getProduct().getId());
        if (price.getPrice() == _price.getPrice())
            throw new ApplicationException(ApplicationExceptionCause.PRICE_CURRENT);
    }
}
