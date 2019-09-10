package com.myApp.security.dao;

import com.myApp.model.UserApp;

public interface UserAppDao {

    UserApp findByUsername(String username);

    Boolean existsByUsername(String username);

    UserApp createUser(UserApp userApp);
}
