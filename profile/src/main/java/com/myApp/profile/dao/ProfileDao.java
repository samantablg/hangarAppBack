package com.myApp.profile.dao;

import com.myApp.model.UserProfile;

import java.util.List;

public interface ProfileDao {

    List<UserProfile> getProfiles();

    UserProfile save(UserProfile profile);

    UserProfile getProfileById(long id);

    Boolean isProfileById(long id);

}
