package com.myApp.price.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.exception.GeneralException;
import com.myApp.price.dao.PriceDAO;
import com.myApp.price.dto.ProductExtended;
import com.myApp.price.model.Price;
import com.myApp.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<Price> getAllPricesOfProduct(long id) {
        if(productService.existProduct(id)) {
            Product product = productService.getProduct(id);
            return priceDAO.getAllPricesOfProduct(product);
        } throw new  ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    @Override
    public Price getCurrentPriceOfProduct(long id) {
        if(productService.existProduct(id)) {
            if (priceDAO.isProductWithPrice(id))
                return priceDAO.getLastPriceOfProduct(id);
            throw new ApplicationException(ApplicationExceptionCause.PROD_PRICE);
        } throw new ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    @Override
    public ProductExtended getProductExtendedById(long id_product) {
        Price price = priceDAO.getLastPriceOfProduct(id_product);
        return this.shapeProductExtended(price);
    }

    @Override
    public List<ProductExtended> getProductsExtended() {
        List<Product> products = this.filterProductsWithPrice();
        if (!products.isEmpty()) {
            return products.stream()
                    .map( product -> {
                        Price _price = priceDAO.getLastPriceOfProduct(product.getId());
                        return this.shapeProductExtended(_price);
                    })
                    .collect(Collectors.toList());
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
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

    private ProductExtended shapeProductExtended(Price price) {
        ProductExtended product = new ProductExtended();
        product.setPrice(price.getPrice());
        product.setName(price.getProduct().getName());
        product.setDescription(price.getProduct().getDescription());
        product.setId(price.getProduct().getId());
        product.setState(price.getProduct().isState());
        return product;
    }

    private List<Product> filterProductsWithPrice() {
        List<Product> products = productService.getAllProducts();
        List<Price> prices = priceDAO.getAllPrices();
        List<Product> _products = new ArrayList();

        products.stream()
                .forEach( product ->
                        prices.stream()
                        .forEach( price -> {
                            if (price.getProduct().equals(product) && !_products.contains(product))
                                _products.add(product);
                            }
                        )
                );
        return _products;
    }
}
