package com.myApp.security.dao;

import com.myApp.model.UserApp;
import com.myApp.security.model.Role;
import com.myApp.security.model.User_Role;

import java.util.List;

public interface UserAppDao {

    UserApp findByUsername(String username);

    boolean existsByUsername(String username);

    UserApp saveUser(UserApp userApp);

    List<UserApp> getUsers();

    void assignRoleToUser(User_Role user_role);

    User_Role findRoleByUserId(long user);

    Role getRoleById(long role);

}
