package com.myApp.price.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.price.dao.PriceDAO;
import com.myApp.model.ProductExtended;
import com.myApp.price.model.Price;
import com.myApp.model.Product;
import com.myApp.product.service.ProductServiceImpl;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceDAO priceDAO;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private Product_HangarServiceImpl product_hangarService;


    @Override
    public List<Price> getAllPrices() {
        List<Price> prices = priceDAO.getAllPrices();
        if (!prices.isEmpty())
            return prices;
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public Price createEntryPriceToProduct(long id_product, double price) {
        if (productService.isProductById(id_product)) {
            Product product = productService.getProduct(id_product);
            Price _price = this.shapePrice(product, price);
            return priceDAO.createEntryPrice(_price);
        } throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<Price> getAllPricesOfProduct(long id) {
        if (productService.isProductById(id)) {
            Product product = productService.getProduct(id);
            return priceDAO.getAllPricesOfProduct(product);
        } throw new  ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    @Override
    public Price getCurrentPriceOfProduct(long id) {
        if (productService.isProductById(id)) {
            if (priceDAO.isProductWithPrice(productService.getProduct(id))) {
                return priceDAO.getLastPriceOfProduct(id);
            } throw new ApplicationException(ApplicationExceptionCause.PROD_PRICE);
        } throw new ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
    }

    @Override
    public ProductExtended getProductExtendedById(long id_product) {
        Product product = productService.getProduct(id_product);
        return this.shapeProductExtendedTest(product);

    }

    @Override
    public List<ProductExtended> getProductsExtended() {
        List<Product> products = productService.getAllActiveProducts();
        return products.stream()
                .map( product -> this.shapeProductExtendedTest(product)).collect(Collectors.toList());
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

    private List<ProductExtended> filterProductsWithPrice() {
        return productService.getAllProducts().stream()
                .map(this::shapeProductExtendedTest)
                .collect(Collectors.toList());
    }

    //TODO extraer código
    private ProductExtended shapeProductExtendedTest(Product product) {
        ProductExtended productExtended = new ProductExtended();
        productExtended.setId(product.getId());
        productExtended.setName(product.getName());
        productExtended.setState(product.isState());
        productExtended.setDescription(product.getDescription());
        try {
            productExtended.setPrice(priceDAO.getLastPriceOfProduct(product.getId()).getPrice());
        } catch(Exception e) {
            double defaultPrice = 0;
            productExtended.setPrice(defaultPrice);
        }
        try {
            List<Long> hangars = product_hangarService.getHangarsOfProduct(product.getId())
                    .stream().map(Product_Hangar::getHangar).collect(Collectors.toList());
            productExtended.setHangars(hangars);
        } catch(Exception e) {
            productExtended.setHangars(new ArrayList<>());
        }

        return productExtended;
    }
}
