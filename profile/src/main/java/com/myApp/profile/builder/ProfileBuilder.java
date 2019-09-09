package com.myApp.profile.builder;

import com.myApp.model.UserProfile;
import com.myApp.profile.dto.ProfileDto;

public class ProfileBuilder {

    private UserProfile profile;

    public ProfileBuilder(ProfileDto profileDto) {
        this.profile = new UserProfile();

        this.profile.setId(profileDto.getId());
        this.profile.setName(profileDto.getName());
        this.profile.setSurname(profileDto.getSurname());
        this.profile.setPhone(profileDto.getPhone());
        this.profile.setMail(profileDto.getMail());
        this.profile.setAddress(profileDto.getAddress());
    }

    public UserProfile getProfile() {
        return profile;
    }
}
