package com.myApp.order.service;

import com.myApp.model.UserApp;
import com.myApp.model.UserProfile;
import com.myApp.order.dao.OrderDaoImpl;
import com.myApp.order.model.Order;
import com.myApp.order.model.Product_Order;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarService;
import com.myApp.profile.service.ProfileService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Mock
    OrderDaoImpl orderDao;
    @Mock
    Product_OrderServiceImpl product_orderService;
    @Mock
    Product_HangarService product_hangarService;
    @Mock
    ProfileService profileService;
    @InjectMocks
    OrderServiceImpl orderServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrders() throws Exception {
        when(orderDao.getOrders()).thenReturn(Arrays.<Order>asList(new Order()));

        List<Order> result = orderServiceImpl.getOrders();
        Assert.assertEquals(Arrays.<Order>asList(new Order()), result);
    }

    @Test
    public void testGetOrdersOfClient() throws Exception {
        when(orderDao.getOrdersOfClient(any())).thenReturn(Arrays.<Order>asList(new Order()));
        when(orderDao.isOrderOfClient(any())).thenReturn(true);
        when(profileService.getUserProfileById(anyLong())).thenReturn(new UserProfile(new UserApp()));

        List<Order> result = orderServiceImpl.getOrdersOfClient(0L);
        Assert.assertEquals(Arrays.<Order>asList(new Order()), result);
    }

    @Test
    public void testSaveOrder() throws Exception {
        when(orderDao.saveOrder(any())).thenReturn(new Order());
        when(product_orderService.getTotalPriceOfProductOrdered(any())).thenReturn(0d);
        when(product_hangarService.updateAmountAfterOrder(anyLong(), anyLong(), anyLong())).thenReturn(new Product_Hangar());
        when(profileService.getUserProfileById(anyLong())).thenReturn(new UserProfile(new UserApp()));

        Order result = orderServiceImpl.saveOrder(new Order(), 0L);
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testDeleteOrder() throws Exception {
        when(orderDao.isOrderById(anyLong())).thenReturn(true);

        boolean result = orderServiceImpl.deleteOrder(0L);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testDeleteProduct_Order() throws Exception {
        when(orderDao.saveOrder(any())).thenReturn(new Order());
        when(orderDao.getOrderById(anyLong())).thenReturn(new Order());
        when(orderDao.isOrderById(anyLong())).thenReturn(true);
        when(product_orderService.getTotalPriceOfProductOrdered(any())).thenReturn(0d);
        when(product_orderService.getProduct_order(anyLong())).thenReturn(new Product_Order());
        when(product_orderService.isProduct_Order(anyLong())).thenReturn(true);

        Order result = orderServiceImpl.deleteProduct_Order(0L, 0L);
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testAddProductToOrder() throws Exception {
        when(orderDao.saveOrder(any())).thenReturn(new Order());
        when(orderDao.getOrderById(anyLong())).thenReturn(new Order());
        when(orderDao.isOrderById(anyLong())).thenReturn(true);
        when(product_orderService.getTotalPriceOfProductOrdered(any())).thenReturn(0d);

        Order result = orderServiceImpl.addProductToOrder(0L, new Product_Order());
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testUpdateProductOfOrder() throws Exception {
        when(orderDao.saveOrder(any())).thenReturn(new Order());
        when(orderDao.getOrderById(anyLong())).thenReturn(new Order());
        when(orderDao.isOrderById(anyLong())).thenReturn(true);
        when(product_orderService.getTotalPriceOfProductOrdered(any())).thenReturn(0d);
        when(product_orderService.getProduct_order(anyLong())).thenReturn(new Product_Order());

        Order result = orderServiceImpl.updateProductOfOrder(0L, 0L, 0L);
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testGetOrderByIdAndProfile() throws Exception {
        when(orderDao.getOrderByIdAndProfile(anyLong(), any())).thenReturn(new Order());
        when(orderDao.isOrderById(anyLong())).thenReturn(true);
        when(orderDao.isOrderByIdAndProfile(anyLong(), any())).thenReturn(true);
        when(profileService.getUserProfileById(anyLong())).thenReturn(new UserProfile(new UserApp()));

        Order result = orderServiceImpl.getOrderByIdAndProfile(0L, 0L);
        Assert.assertEquals(new Order(), result);
    }

    @Test
    public void testGetOrderById() throws Exception {
        when(orderDao.getOrderById(anyLong())).thenReturn(new Order());
        when(orderDao.isOrderById(anyLong())).thenReturn(true);

        Order result = orderServiceImpl.getOrderById(0L);
        Assert.assertEquals(new Order(), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme