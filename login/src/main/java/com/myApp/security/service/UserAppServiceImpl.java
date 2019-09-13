package com.myApp.security.service;

import com.myApp.model.UserApp;
import com.myApp.security.config.JwtTokenUtil;
import com.myApp.security.dao.UserAppDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserAppDao userAppDao;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override
    public List<UserApp> listUsers() {
        List<UserApp> result = userAppDao.getUsers();
        if (!result.isEmpty())
            return result;
        throw new UsernameNotFoundException("Users not found");
    }

    @Override
    public boolean existsByUsername(String username) {
        return (userAppDao.findByUsername(username) != null);
    }

    @Override
    public String getUsernameByToken(String token) {
        return tokenUtil.getUsernameFromToken(token);
    }

    @Override
    public long getIdByToken(String token) {
        String _token = token.replace("Bearer ", "");
        return this.getIdByUsername(tokenUtil.getUsernameFromToken(_token));
    }

    private long getIdByUsername(String username) {
        return userAppDao.findByUsername(username).getId();
    }

}
