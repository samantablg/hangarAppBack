package com.myApp.security.builder;

import com.myApp.security.model.UserApp;
import com.myApp.security.model.User_Role;

public class User_RoleBuilder {

    private User_Role user_role;

    public User_RoleBuilder(UserApp userApp) {
        this.user_role = new User_Role();

        this.user_role.setUser(userApp.getId());
        this.user_role.setRole(2);
    }

    public User_Role getUser_role() {
        return user_role;
    }
}
