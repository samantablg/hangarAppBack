package com.myApp.order.service;

import com.myApp.order.dao.Product_OrderDao;
import com.myApp.order.model.Product_Order;
import com.myApp.price.model.Price;
import com.myApp.price.service.PriceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Product_OrderServiceImplTest {
    @Mock
    PriceService priceService;
    @Mock
    Product_OrderDao product_orderDao;
    @InjectMocks
    Product_OrderServiceImpl product_OrderServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetTotalPriceOfProductOrdered() throws Exception {
        when(priceService.getCurrentPriceOfProduct(anyLong())).thenReturn(new Price());

        double result = product_OrderServiceImpl.getTotalPriceOfProductOrdered(new Product_Order());
        Assert.assertEquals(0d, result);
    }

    @Test
    public void testDeleteProduct_Order() throws Exception {
        product_OrderServiceImpl.deleteProduct_Order(new Product_Order());
    }

    @Test
    public void testSaveProduct_Order() throws Exception {
        when(product_orderDao.saveProduct_Order(any())).thenReturn(new Product_Order());

        Product_Order result = product_OrderServiceImpl.saveProduct_Order(new Product_Order());
        Assert.assertEquals(new Product_Order(), result);
    }

    @Test
    public void testGetProduct_order() throws Exception {
        when(product_orderDao.getProduct_Order(anyLong())).thenReturn(new Product_Order());

        Product_Order result = product_OrderServiceImpl.getProduct_order(0L);
        Assert.assertEquals(new Product_Order(), result);
    }

    @Test
    public void testIsProduct_Order() throws Exception {
        when(product_orderDao.isProduct_Order(anyLong())).thenReturn(true);

        boolean result = product_OrderServiceImpl.isProduct_Order(0L);
        Assert.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme