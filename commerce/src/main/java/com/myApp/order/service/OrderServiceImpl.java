package com.myApp.order.service;

import com.myApp.model.Order;
import com.myApp.model.Product_Order;
import com.myApp.order.dao.OrderDaoImpl;

import com.myApp.product_hangar.service.Product_HangarService;
import com.myApp.product_order.service.Product_OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDaoImpl orderDao;

    @Autowired
    private Product_OrderServiceImpl product_orderService;

    @Autowired
    private Product_HangarService product_hangarService;

    @Override
    public Order saveOrder(Order order) {

        try { //TODO cambiar relación en las entidades para que se guarde product_order solo
            List<Product_Order> products_order = order.getProducts_orders().stream().map(
                    product_order -> {
                        product_hangarService.updateAmountAfterOrder(product_order.getProduct_id(),product_order.getHangar_id(),product_order.getQuantity());
                        return product_orderService.save(product_order);
                    }
              ).collect(Collectors.toList());
            order.setProducts_orders(products_order);
            return orderDao.saveOrder(order);
        } catch( Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
