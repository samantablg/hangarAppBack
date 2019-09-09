package com.myApp.security.builder;

import com.myApp.security.dto.UserAppDto;
import com.myApp.model.UserApp;

public class UserAppBuilder {

    private UserApp userApp;

    public UserAppBuilder(UserAppDto userAppDto) {
        this.userApp = new UserApp();

        this.userApp.setId(userAppDto.getId());
        this.userApp.setUsername(userAppDto.getUsername());
        this.userApp.setPassword(userAppDto.getPassword());
    }

    public UserApp getUserApp() {
        return userApp;
    }
}
