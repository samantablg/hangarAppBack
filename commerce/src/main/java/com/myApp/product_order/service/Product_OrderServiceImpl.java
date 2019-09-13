package com.myApp.product_order.service;

import com.myApp.model.Product;
import com.myApp.price.model.Price;
import com.myApp.price.service.PriceService;
import com.myApp.product_order.dao.Product_OrderDao;
import com.myApp.product_order.model.Product_Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_OrderServiceImpl implements Product_OrderService {

    @Autowired
    private Product_OrderDao product_orderDao;

    @Autowired
    private PriceService priceService;

    @Override
    public Product_Order save(Product_Order product_order) {
        return product_orderDao.save(product_order);
    }

    @Override
    public double getTotalPriceOfProductOrdered(Product_Order product_order) {
        double price = getCurrentPriceOfProduct(product_order.getProduct_id());
        return price * product_order.getQuantity();
    }

    private double getCurrentPriceOfProduct(long product) {
        return this.priceService.getCurrentPriceOfProduct(product).getPrice();
    }
}
