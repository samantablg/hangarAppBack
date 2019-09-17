package com.myApp.order.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.order.dao.OrderDaoImpl;
import com.myApp.order.model.Order;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarService;
import com.myApp.product_order.model.Product_Order;
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
        List<Product_Order> products_order = this.saveProduct_Order(order);
        order.setProducts_orders(products_order);
        if (order.getTotal_price() == this.calculatePriceOfOrder(order) && order.getTotal_products() == this.getTotalProductsOrder(order))
            return orderDao.saveOrder(order);
        throw new ApplicationException(ApplicationExceptionCause.PRICE_CONFLICT); //change conflict
    }

    @Override
    public void deleteOrder(long id) {
        if (orderDao.isOrderById(id))
            orderDao.deleteOrder(id);
        throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override //TODO definir que quiero actualizar
    public Order updateOrder(long id) {
        return null;
    }

    @Override
    public Order deleteProduct_Order(long id, Product_Order product_order) {
        if (orderDao.isOrderById(id)) {
            Order order = orderDao.getOrderById(id);
            order.getProducts_orders().remove(product_order);
            return order;
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public List<Order> getOrdersOfClient(long id) {
        if (orderDao.isOrderOfClient(id))
            return orderDao.getOrdersOfClient(id);
        throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    private double calculatePriceOfOrder(Order order) {
        return order.getProducts_orders().stream().mapToDouble(
                product_order -> product_orderService.getTotalPriceOfProductOrdered(product_order)
        ).sum();
    }

    private List<Product_Order> saveProduct_Order(Order order) {
        return order.getProducts_orders().stream().map(
                product_order -> {
                    updateStockAfterOrder(product_order);
                    return product_orderService.save(product_order);
                }
        ).collect(Collectors.toList());
    }

    private Product_Hangar updateStockAfterOrder(Product_Order product_order) {
        return product_hangarService.updateAmountAfterOrder(product_order.getProduct_id(), product_order.getHangar_id(), product_order.getQuantity());
    }

    private long getTotalProductsOrder(Order order) {
        return order.getProducts_orders().stream().mapToLong(
                product_order -> product_order.getQuantity()
        ).sum();
    }

}
