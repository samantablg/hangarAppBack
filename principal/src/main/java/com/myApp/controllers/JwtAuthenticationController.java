package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.model.UserProfile;
import com.myApp.profile.builder.ProfileBuilder;
import com.myApp.profile.builder.ProfileDtoBuilder;
import com.myApp.profile.dto.ProfileDto;
import com.myApp.profile.service.ProfileService;


import com.myApp.model.UserApp;
import com.myApp.security.config.JwtTokenUtil;
import com.myApp.security.dto.UserAppDto;
import com.myApp.security.model.UserAppResponse;
import com.myApp.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserApp authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new UserAppResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserAppDto user) throws Exception {
        if(!user.getUsername().isEmpty() && !user.getPassword().isEmpty()) {
            return new ResponseEntity<>(userDetailsService.save(user), HttpStatus.OK);
        } throw new Exception("INVALID_CREDENTIALS");

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

    //TODO esto solo debe permitirse para un administrador, y tiene que mostrarse la contraseña sin cifrar
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> listUsers() throws Exception {
        return new ResponseEntity<>(
                userDetailsService.listUsers(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value ="/register/{username}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> findByUsername(@PathVariable String username) {
        boolean user = userDetailsService.existsByUsername(username);
        if(user) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestHeader(value = "Authorization") String token, @RequestBody ProfileDto profileDto) {

        if(profileDto.getId()<=0) {
            throw new ControllerException.idNotAllowed(profileDto.getId());
        }
        String _token = token.replace("Bearer ", "");
        long id = userDetailsService.getIdByUsername(jwtTokenUtil.getUsernameFromToken(_token));

        if( id == profileDto.getId() ) {
            UserProfile profile = new ProfileBuilder(profileDto).getProfile();
            return new ResponseEntity<>(
                    new ProfileDtoBuilder(profileService.updateProfile(profile)).getProfileDto(),
                    HttpStatus.OK
            );
        } throw new ControllerException.profileUpdateNotAllowed();


    }
}