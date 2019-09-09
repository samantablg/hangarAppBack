package com.myApp.profile.dao;

import com.myApp.model.UserProfile;
import com.myApp.profile.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProfileDaoImpl implements ProfileDao {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserProfile save(UserProfile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Boolean existById(UserProfile profile) {
        return profileRepository.existsById(profile.getId());
    }

    @Override
    public UserProfile getProfileById(long id) {
        return profileRepository.getOne(id);
    }

    @Override
    public List<UserProfile> getProfiles() {
        return profileRepository.findAll();
    }
}
