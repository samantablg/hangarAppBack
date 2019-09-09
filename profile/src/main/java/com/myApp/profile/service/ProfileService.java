package com.myApp.profile.service;

import com.myApp.model.UserProfile;

import java.util.List;

public interface ProfileService {

    UserProfile save(UserProfile profile);

    UserProfile getUserProfileById(long id);

    List<UserProfile> getAllUsers();


}
