package com.myApp.profile.service;

import com.myApp.exception.ApplicationException;
import com.myApp.exception.ApplicationExceptionCause;
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
        if (profileDao.isProfileById(id))
            return profileDao.getProfileById(id);
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        List<UserProfile> profiles = profileDao.getProfiles();
        if (!profiles.isEmpty())
            return profiles;
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    @Override
    public UserProfile updateProfile(UserProfile profile) {

        if(profileDao.isProfileById(profile.getId())) {
            return this.manageUpdate(profile);
        }
        throw new ApplicationException(ApplicationExceptionCause.NOT_FOUND);
    }

    private UserProfile manageUpdate(UserProfile profile) {
        UserProfile _profile = profileDao.getProfileById(profile.getId());
        _profile.setName(profile.getName());
        _profile.setSurname(profile.getSurname());
        _profile.setAddress(profile.getAddress());
        _profile.setMail(profile.getMail());
        _profile.setPhone(profile.getPhone());
        return _profile;
    }

}
