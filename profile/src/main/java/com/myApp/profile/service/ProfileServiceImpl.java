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

    @Override //TODO mejorar código -> hacer el builder en el controlador cuando recibo el profileDto y mandarlo ya actualizado al servicio
    public UserProfile updateProfile(UserProfile profile) {

        if(profileDao.existById(profile)) {
            UserProfile _profile = profileDao.getProfileById(profile.getId());
            _profile.setName(profile.getName());
            _profile.setSurname(profile.getSurname());
            _profile.setAddress(profile.getAddress());
            _profile.setMail(profile.getMail());
            _profile.setPhone(profile.getPhone());
            return profileDao.save(profile);
        }
        return null; //TODO lanzar excepcion
    }

    @Override
    public long getIdByUsername(String username) {
        return 5; //profileDao.ge;
    }
}
