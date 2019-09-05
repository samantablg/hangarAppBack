package com.myApp.userProfile.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "userprofile")
@Getter
@Setter
public class UserProfile {

    @Column(name = "user_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surename")
    private String sureName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "address")
    private String address;

}