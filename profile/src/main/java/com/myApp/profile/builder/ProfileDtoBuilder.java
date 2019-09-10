package com.myApp.profile.builder;

import com.myApp.model.UserProfile;
import com.myApp.profile.dto.ProfileDto;


public class ProfileDtoBuilder {

    private ProfileDto profileDto;

    public ProfileDtoBuilder(UserProfile profile) {
        this.profileDto = new ProfileDto();

        this.profileDto.setId(profile.getId());
        this.profileDto.setName(profile.getName());
        this.profileDto.setSurname(profile.getSurname());
        this.profileDto.setPhone(profile.getPhone());
        this.profileDto.setMail(profile.getMail());
        this.profileDto.setAddress(profile.getAddress());
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }
}
