package com.myApp.hangar.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HangarDto {

    private long id;
    private String name;
    private String address;
    private String owner;
    private String email;
    private long phone;
    private boolean state;
}
