package com.myApp.security.builder;

import com.myApp.security.dto.UserAppDto;
import com.myApp.model.UserApp;

public class UserAppDtoBuilder {

    private UserAppDto userAppDto;

    public UserAppDtoBuilder(UserApp userApp) {
        this.userAppDto = new UserAppDto();

        this.userAppDto.setId(userApp.getId());
        this.userAppDto.setUsername(userApp.getUsername());
        this.userAppDto.setPassword(userApp.getPassword());
    }

    public UserAppDto getUserAppDto() {
        return userAppDto;
    }
}
