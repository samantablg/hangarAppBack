package com.myApp.hangar.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class HangarDto {

    private long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    private String owner;
    private String email;
    private long phone;
    private boolean state;
}
