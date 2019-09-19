package com.myApp.order.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
import com.myApp.model.Product;
import com.myApp.model.UserProfile;
import com.myApp.order.dao.OrderDaoImpl;
import com.myApp.order.model.Order;
import com.myApp.product_hangar.service.Product_HangarService;
import com.myApp.order.model.Product_Order;
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
            if (order.getTotal_products() == this.calculateTotalProductsOrder(order)) {
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
    public Order deleteProduct_Order(long id_order, long id_product_order) { //TODO falta manejar el stock y no puede haber una orden sin productos!
        if (orderDao.isOrderById(id_order)) {
            if (product_orderService.isProduct_Order(id_product_order)) {
                Product_Order product_order = product_orderService.getProduct_order(id_product_order);
                Order order = orderDao.getOrderById(id_order);
                this.removeToOrder(order, product_order);
                this.manageDataOrder(order);
                return orderDao.saveOrder(order);
            } throw new ApplicationException(ApplicationExceptionCause.PROD_ORDER_NOT_FOUND);
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order addProductToOrder(long id_order, Product_Order product_order) { //TODO falta manejar el stock

        if (orderDao.isOrderById(id_order)) {
            Order order = orderDao.getOrderById(id_order);
            if (!this.isProductInOrder(order, product_order)) {
                order.getProducts_orders().add(product_order);
                this.manageDataOrder(order);
                return orderDao.saveOrder(order);
            } throw new ApplicationException(ApplicationExceptionCause.PRODUCT_CONFLICT);
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order updateProductOfOrder(long id_order, long id_product_order, long quantity) { //TODO falta manejar el stock
        if (orderDao.isOrderById(id_order)) {
            Order order = orderDao.getOrderById(id_order);
            Product_Order product_order = product_orderService.getProduct_order(id_product_order);
            if (this.isProductInOrder(order, product_order)) {
                int _index = order.getProducts_orders().indexOf(product_order);
                order.getProducts_orders().get(_index).setQuantity(quantity);
                this.manageDataOrder(order);
                return orderDao.saveOrder(order);
            } throw new ApplicationException(ApplicationExceptionCause.PROD_NOT_FOUND);
        } throw new ApplicationException(ApplicationExceptionCause.ORDER_NOT_FOUND);
    }

    @Override
    public Order getOrderByIdAndProfile(long id, long id_profile) {
        UserProfile profile = profileService.getUserProfileById(id_profile);
        if (orderDao.isOrderById(id)) {
            if (orderDao.isOrderByIdAndProfile(id, profile))
                return orderDao.getOrderByIdAndProfile(id, profile);
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

    private long calculateTotalProductsOrder(Order order) {
        return order.getProducts_orders().stream().mapToLong(
                product_order -> product_order.getQuantity()
        ).sum();
    }

    private void manageDataOrder(Order order) {
        order.setTotal_price(this.calculatePriceOfOrder(order));
        order.setTotal_products(this.calculateTotalProductsOrder(order));
    }


    private void removeToOrder(Order order, Product_Order product_order) {
        order.getProducts_orders().remove(product_order);
        /*if (order.getProducts_orders().contains(product_order))
            order.getProducts_orders().remove(product_order);
        throw new ApplicationException(ApplicationExceptionCause.PROD_ORDER_NOT_FOUND);*/
    }

    private boolean isProductInOrder(Order order, Product_Order product_order) { //TODO mejorar código
        List<Product_Order> product_orders = order.getProducts_orders();
        List<Product_Order> _product_orders = product_orders.stream()
                .filter(_product_order ->
                        (_product_order.getHangar_id() == product_order.getHangar_id() && _product_order.getProduct_id() == product_order.getProduct_id()))
                .collect(Collectors.toList());
        return !(_product_orders.isEmpty());
    }

}
