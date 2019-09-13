package com.myApp.security.service;

import com.myApp.model.UserApp;

import java.util.List;

public interface UserAppService {

    public List<UserApp> listUsers();

    public boolean existsByUsername(String username);

    public String getUsernameByToken(String token);

    public long getIdByToken(String token);
}
