package com.myApp.controllers;

import com.myApp.model.UserApp;
import com.myApp.security.builder.UserAppBuilder;
import com.myApp.security.builder.UserAppDtoBuilder;
import com.myApp.security.dto.UserAppDto;
import com.myApp.security.model.UserAppResponse;
import com.myApp.security.service.JwtUserDetailsServiceImpl;
import com.myApp.security.service.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserAppService userAppService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserApp authenticationRequest) throws Exception {
        this.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = userDetailsService.generateToken(userDetails);

        return ResponseEntity.ok(new UserAppResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserAppDto> saveUser(@Valid @RequestBody UserAppDto userDto) throws Exception {
        UserApp user = userDetailsService.save(new UserAppBuilder(userDto).getUserApp());
        return new ResponseEntity<>(
                new UserAppDtoBuilder(user).getUserAppDto(),
                HttpStatus.OK
        );
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET) //TODO esto solo debe permitirse para un administrador
    public ResponseEntity<List<UserApp>> listUsers() throws Exception {
        return new ResponseEntity<>(
                userAppService.listUsers(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value ="/register/{username}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> findByUsername(@PathVariable String username) throws Exception {
        return new ResponseEntity<Boolean>(userAppService.existsByUsername(username), HttpStatus.OK);
    }

}