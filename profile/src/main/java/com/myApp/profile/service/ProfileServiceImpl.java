package com.myApp.profile.service;

import com.myApp.model.UserProfile;
import com.myApp.profile.dao.ProfileDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDaoImpl profileDao;

    @Override
    public UserProfile save(UserProfile profile) {
        return profileDao.save(profile);
    }

    @Override
    public UserProfile getUserProfileById(long id) {
        return profileDao.getProfileById(id);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return profileDao.getProfiles();
    }
}
