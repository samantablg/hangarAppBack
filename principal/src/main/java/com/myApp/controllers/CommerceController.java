package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.order.model.Order;
import com.myApp.model.UserProfile;
import com.myApp.order.builder.OrderBuilder;
import com.myApp.order.builder.OrderDtoBuilder;
import com.myApp.order.dto.OrderDto;
import com.myApp.order.service.OrderService;
import com.myApp.order.service.OrderServiceImpl;
import com.myApp.profile.builder.ProfileBuilder;
import com.myApp.profile.builder.ProfileDtoBuilder;
import com.myApp.profile.dto.ProfileDto;
import com.myApp.profile.service.ProfileService;
import com.myApp.security.service.UserAppService;
import com.myApp.util.Util;
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
    private OrderServiceImpl orderService;

    @Autowired
    private ProfileService profileService;

   @Autowired
   private UserAppService userAppService;

   @Autowired
   private Util util;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> saveOrder(@RequestHeader(value = "Authorization") String token, @RequestBody OrderDto orderDto) throws Exception {

        if (userAppService.getIdByToken(token) == orderDto.getProfile().getId()) {
            Order order = new OrderBuilder(orderDto).getOrder();
            Order _order = orderService.saveOrder(order);
            return new ResponseEntity<>(
                    new OrderDtoBuilder(_order).getOrderDto(),
                    HttpStatus.OK
            );
        } throw new ControllerException.methodNotAllowed();
    }

    @PostMapping("/profile")
    public ResponseEntity<ProfileDto> saveProfile(@RequestHeader(value = "Authorization") String token, @RequestBody ProfileDto profileDto) throws Exception {

        util.checkId(profileDto.getId());
        if (userAppService.getIdByToken(token) == profileDto.getId()) {
            UserProfile profile = new ProfileBuilder(profileDto).getProfile();
            return new ResponseEntity<>(
                    new ProfileDtoBuilder(profileService.save(profile)).getProfileDto(),
                    HttpStatus.CREATED
            );
        } throw new ControllerException.methodNotAllowed();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ProfileDto> updateUser(@Valid @RequestBody ProfileDto profileDto) throws Exception {
        UserProfile profile = new ProfileBuilder(profileDto).getProfile();
        final UserProfile _profile = profileService.updateProfile(profile);
        return new ResponseEntity<>(
                new ProfileDtoBuilder(_profile).getProfileDto(),
                HttpStatus.OK
        );
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() throws Exception {
        List<UserProfile> profiles = profileService.getAllUsers();
        return new ResponseEntity<>(
                profiles.stream().map(
                        profile -> new ProfileDtoBuilder(profile).getProfileDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable long id) throws Exception {
        UserProfile profile = profileService.getUserProfileById(id);
        return new ResponseEntity<>(
                new ProfileDtoBuilder(profile).getProfileDto(),
                HttpStatus.OK
        );
    }
}
