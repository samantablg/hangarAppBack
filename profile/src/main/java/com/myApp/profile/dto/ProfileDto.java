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
    @NotNull @Min(3)
    private String name;
    @NotNull @Min(3)
    private String surname;
    private String phone;
    @NotNull @Email
    private String mail;
    private String address;

}
