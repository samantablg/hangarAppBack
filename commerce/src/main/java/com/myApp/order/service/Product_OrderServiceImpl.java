package com.myApp.order.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.order.dao.Product_OrderDao;
import com.myApp.order.service.Product_OrderService;
import com.myApp.price.service.PriceService;
import com.myApp.order.model.Product_Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_OrderServiceImpl implements Product_OrderService {


    @Autowired
    private PriceService priceService;

    @Autowired
    private Product_OrderDao product_orderDao;

    @Override

    public double getTotalPriceOfProductOrdered(Product_Order product_order) {
        double price = this.getCurrentPriceOfProduct(product_order.getProduct_id());
        return price * product_order.getQuantity();
    }

    private double getCurrentPriceOfProduct(long product) {
        try {
            return this.priceService.getCurrentPriceOfProduct(product).getPrice();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void deleteProduct_Order(Product_Order product_order) {
        product_orderDao.deleteProduct_Order(product_order);
    }

    @Override
    public Product_Order saveProduct_Order(Product_Order product_order) {
        return product_orderDao.saveProduct_Order(product_order);
    }

    @Override
    public Product_Order getProduct_order(long id_product_order) {
        return product_orderDao.getProduct_Order(id_product_order);
        /*if (product_orderDao.isProduct_Order(id_product_order))
            return product_orderDao.getProduct_Order(id_product_order);
        throw new ApplicationException(ApplicationExceptionCause.PROD_ORDER_NOT_FOUND);*/
    }

    @Override
    public boolean isProduct_Order(long id_product_order) {
        return product_orderDao.isProduct_Order(id_product_order);
    }
}
