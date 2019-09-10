package com.myApp.profile.service;

import com.myApp.model.UserProfile;
import com.myApp.profile.dao.ProfileDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public UserProfile updateProfile(UserProfile update_profile) {

        if(profileDao.existById(update_profile)) {
            UserProfile profile = profileDao.getProfileById(update_profile.getId());
            profile.setName(update_profile.getName());
            profile.setSurname(update_profile.getSurname());
            profile.setAddress(update_profile.getAddress());
            profile.setMail(update_profile.getMail());
            profile.setPhone(update_profile.getPhone());
            return profileDao.save(profile);
        }
        return null;
    }
}
