package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.model.Order;
import com.myApp.model.UserProfile;
import com.myApp.order.builder.OrderBuilder;
import com.myApp.order.builder.OrderDtoBuilder;
import com.myApp.order.dto.OrderDto;
import com.myApp.order.service.OrderService;
import com.myApp.product_order.service.Product_OrderService;
import com.myApp.profile.builder.ProfileBuilder;
import com.myApp.profile.builder.ProfileDtoBuilder;
import com.myApp.profile.dto.ProfileDto;
import com.myApp.profile.service.ProfileService;
import com.myApp.security.config.JwtTokenUtil;
import com.myApp.security.service.JwtUserDetailsService;
import com.myApp.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/commerce")
public class CommerceController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Product_OrderService product_orderService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SecurityUtils securityUtils;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> saveOrderTest(@RequestHeader(value = "Authorization") String token, @RequestBody OrderDto orderDto) throws Exception {
        if(securityUtils.getIdByToken(token) == orderDto.getProfile().getId()) {
            Order order = new OrderBuilder(orderDto).getOrder();
            return new ResponseEntity<>(
                    new OrderDtoBuilder(orderService.saveOrder(order)).getOrderDto(),
                    HttpStatus.OK
            );
        } throw new ControllerException.methodNotAllowed();
    }

    @PostMapping("/testProfile")
    public ResponseEntity<ProfileDto> saveProfile(@RequestBody ProfileDto profileDto) throws Exception {

        UserProfile profile = new ProfileBuilder(profileDto).getProfile();
        return new ResponseEntity<>(
                new ProfileDtoBuilder(profileService.save(profile)).getProfileDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/testGet")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() throws Exception {
        List<UserProfile> profiles = profileService.getAllUsers();
        return new ResponseEntity<>(
                profiles.stream().map(
                        profile -> new ProfileDtoBuilder(profile).getProfileDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/testGetById/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable long id) throws Exception {
        UserProfile profile = profileService.getUserProfileById(id);
        return new ResponseEntity<>(
                new ProfileDtoBuilder(profile).getProfileDto(),
                HttpStatus.OK
        );
    }
}
