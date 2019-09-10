package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.model.UserProfile;
import com.myApp.order.builder.OrderBuilder;
import com.myApp.order.builder.OrderDtoBuilder;
import com.myApp.order.dto.OrderDto;
import com.myApp.order.model.Order;
import com.myApp.order.service.OrderService;
import com.myApp.product_order.service.Product_OrderService;
import com.myApp.profile.builder.ProfileBuilder;
import com.myApp.profile.builder.ProfileDtoBuilder;
import com.myApp.profile.dto.ProfileDto;
import com.myApp.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/testOrder")
    public ResponseEntity<OrderDto> saveOrderTest(@RequestBody OrderDto orderDto) {
        Order order = new OrderBuilder(orderDto).getOrder();
        Order newOrder = orderService.saveOrder(order);
        return new ResponseEntity<>(
                new OrderDtoBuilder(order).getOrderDto(),
                HttpStatus.OK
        );
    }

    @PostMapping("/testProfile")
    public ResponseEntity<ProfileDto> saveProfile(@RequestBody ProfileDto profileDto) {

        UserProfile profile = new ProfileBuilder(profileDto).getProfile();
        return new ResponseEntity<>(
                new ProfileDtoBuilder(profileService.save(profile)).getProfileDto(),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/testGet")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<UserProfile> profiles = profileService.getAllUsers();
        return new ResponseEntity<>(
                profiles.stream().map(
                        profile -> new ProfileDtoBuilder(profile).getProfileDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/testGetById/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable long id) {
        UserProfile profile = profileService.getUserProfileById(id);
        return new ResponseEntity<>(
                new ProfileDtoBuilder(profile).getProfileDto(),
                HttpStatus.OK
        );
    }

    @PutMapping("/user/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto profileDto) {

        if(profileDto.getId()>=0) {
            UserProfile profile = new ProfileBuilder(profileDto).getProfile();
            return new ResponseEntity<>(
                    new ProfileDtoBuilder(profileService.updateProfile(profile)).getProfileDto(),
                    HttpStatus.OK
            );
        } throw new ControllerException.idNotAllowed(profileDto.getId());
    }

}
