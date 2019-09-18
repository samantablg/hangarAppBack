package com.myApp.order.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.model.UserProfile;
import com.myApp.order.dao.OrderDaoImpl;
import com.myApp.order.model.Order;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarService;
import com.myApp.product_order.model.Product_Order;
import com.myApp.product_order.service.Product_OrderServiceImpl;
import com.myApp.profile.service.ProfileService;
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

    @Autowired
    private ProfileService profileService;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = orderDao.getOrders();
        if (!orders.isEmpty())
            return orders;
        throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public List<Order> getOrdersOfClient(long id) {
        UserProfile profile = profileService.getUserProfileById(id);
        if (orderDao.isOrderOfClient(profile)) {
            return orderDao.getOrdersOfClient(profile);
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order saveOrder(Order order) {
        if (order.getTotal_price() == this.calculatePriceOfOrder(order)) {
            if (order.getTotal_products() == this.getTotalProductsOrder(order)) {
                List<Product_Order> products_order = this.updateStockAfterOrder(order);
                order.setProducts_orders(products_order);
                return orderDao.saveOrder(order);
            } throw new ApplicationException(ApplicationExceptionCause.ITEMS_CONFLICT);
        } throw new ApplicationException(ApplicationExceptionCause.PRICE_CONFLICT);
    }

    @Override
    public boolean deleteOrder(long id) {
        if (orderDao.isOrderById(id)) {
            orderDao.deleteOrder(id);
            return true;
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order updateOrder(Order order) { //TODO voy a comprobar los product_order para ver si han cambiado o no
        Order _order = orderDao.getOrderById(order.getId());
        return null;
    }

    @Override // ¿Es necesaria?
    public Order deleteProduct_Order(long id, Product_Order product_order) {
        if (orderDao.isOrderById(id)) {
            Order order = orderDao.getOrderById(id);
            order.getProducts_orders().remove(product_order);
            return order;
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order getOrderByIdAndProfile(long id, long profile) {
        UserProfile _profile = profileService.getUserProfileById(profile);
        if (orderDao.isOrderById(id)) {
            if (orderDao.isOrderByIdAndProfile(id, _profile))
                return orderDao.getOrderByIdAndProfile(id, _profile);
            throw new ApplicationException(ApplicationExceptionCause.CLIENT_NOT_ORDER);
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order getOrderById(long id) {
        if (orderDao.isOrderById(id))
            return orderDao.getOrderById(id);
        throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    private double calculatePriceOfOrder(Order order) {
        return order.getProducts_orders().stream().mapToDouble(
                product_order -> product_orderService.getTotalPriceOfProductOrdered(product_order)
        ).sum();
    }

    private List<Product_Order> updateStockAfterOrder(Order order) {
        return order.getProducts_orders().stream().map(
                product_order -> {
                    product_hangarService.updateAmountAfterOrder(product_order.getProduct_id(), product_order.getHangar_id(), product_order.getQuantity());
                    return product_order;
                }
        ).collect(Collectors.toList());
    }

    private long getTotalProductsOrder(Order order) {
        return order.getProducts_orders().stream().mapToLong(
                product_order -> product_order.getQuantity()
        ).sum();
    }

    private void addToOrder(Order order, Product_Order product_order) {
        List<Product_Order> product_orders = order.getProducts_orders();
        product_orders.add(product_order);
    }

    private void removeToOrder(Order order, Product_Order product_order) {
        List<Product_Order> product_orders = order.getProducts_orders();
        product_orders.remove(product_order);
    }

    private void updateOrder(Order order, Product_Order product_order) {

    }

}
