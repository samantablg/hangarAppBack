package com.myApp.utils;

import com.myApp.security.config.JwtTokenUtil;
import com.myApp.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public long getIdByToken(String token) {
        String _token = token.replace("Bearer ", "");
        return userDetailsService.getIdByUsername(jwtTokenUtil.getUsernameFromToken(_token));
    }
}
