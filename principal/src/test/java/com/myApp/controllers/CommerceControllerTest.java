package com.myApp.controllers;

import com.myApp.model.UserApp;
import com.myApp.model.UserProfile;
import com.myApp.order.dto.OrderDto;
import com.myApp.order.dto.Product_OrderDto;
import com.myApp.order.model.Order;
import com.myApp.order.service.OrderServiceImpl;
import com.myApp.profile.dto.ProfileDto;
import com.myApp.profile.service.ProfileService;
import com.myApp.security.service.UserAppService;
import com.myApp.util.Util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class CommerceControllerTest {
    @Mock
    OrderServiceImpl orderService;
    @Mock
    ProfileService profileService;
    @Mock
    UserAppService userAppService;
    @Mock
    Util util;
    @InjectMocks
    CommerceController commerceController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        when(orderService.getOrders()).thenReturn(Arrays.<Order>asList(new Order()));

        ResponseEntity<List<OrderDto>> result = commerceController.getAllOrders();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testSaveOrder() throws Exception {
        when(orderService.saveOrder(any(), anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<OrderDto> result = commerceController.saveOrder("token", new OrderDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testSaveProfile() throws Exception {
        when(profileService.save(any())).thenReturn(new UserProfile(new UserApp()));
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<ProfileDto> result = commerceController.saveProfile("token", new ProfileDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetOrdersOfClient() throws Exception {
        when(orderService.getOrdersOfClient(anyLong())).thenReturn(Arrays.<Order>asList(new Order()));
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<List<OrderDto>> result = commerceController.getOrdersOfClient("token", 0L);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetOrderByIdOfClient() throws Exception {
        when(orderService.getOrderByIdAndProfile(anyLong(), anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<OrderDto> result = commerceController.getOrderByIdOfClient("token", 0L, 0L);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testDeleteOrderById() throws Exception {
        when(orderService.deleteOrder(anyLong())).thenReturn(true);
        when(orderService.getOrderById(anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<Boolean> result = commerceController.deleteOrderById("token", 0L);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testDeleteProductOfOrder() throws Exception {
        when(orderService.deleteProduct_Order(anyLong(), anyLong())).thenReturn(new Order());
        when(orderService.getOrderById(anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<OrderDto> result = commerceController.deleteProductOfOrder("token", 0L, 0L);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testAddProductToOrder() throws Exception {
        when(orderService.addProductToOrder(anyLong(), any())).thenReturn(new Order());
        when(orderService.getOrderById(anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<OrderDto> result = commerceController.addProductToOrder("token", 0L, new Product_OrderDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testAddProductToOrder2() throws Exception {
        when(orderService.updateProductOfOrder(anyLong(), anyLong(), anyLong())).thenReturn(new Order());
        when(orderService.getOrderById(anyLong())).thenReturn(new Order());
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<Order> result = commerceController.addProductToOrder("token", 0L, 0L, 0L);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testUpdateUser() throws Exception {
        when(profileService.updateProfile(any())).thenReturn(new UserProfile(new UserApp()));

        ResponseEntity<ProfileDto> result = commerceController.updateUser(new ProfileDto());
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetAllProfiles() throws Exception {
        when(profileService.getAllUsers()).thenReturn(Arrays.<UserProfile>asList(new UserProfile(new UserApp())));

        ResponseEntity<List<ProfileDto>> result = commerceController.getAllProfiles();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetProfileOfClient() throws Exception {
        when(profileService.getUserProfileById(anyLong())).thenReturn(new UserProfile(new UserApp()));
        when(userAppService.getIdByToken(anyString())).thenReturn(0L);

        ResponseEntity<ProfileDto> result = commerceController.getProfileOfClient("token");
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme