package com.myApp.profile.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ProfileDto {

    @NotNull
    private long id;
    private String name;
    private String surname;
    private String phone;
    private String mail;
    private String address;

}
