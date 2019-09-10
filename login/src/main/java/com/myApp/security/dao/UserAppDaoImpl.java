package com.myApp.security.dao;

import com.myApp.model.UserApp;
import com.myApp.security.model.UserAppResponse;
import com.myApp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAppDaoImpl implements UserAppDao{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserApp findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserApp createUser(UserApp userApp) {
        return userRepository.save(userApp);
    }
}
