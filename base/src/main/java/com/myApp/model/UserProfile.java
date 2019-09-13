package com.myApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
@Getter @Setter
public class UserProfile {

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "userApp"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name="user_id", unique = true, nullable = false)
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "mail")
    private String mail;
    @Column(name = "address")
    private String address;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JsonIgnore
    private UserApp userApp;

    public UserProfile() {}

    public UserProfile(UserApp userApp) {
        this.userApp = userApp;
        userApp.setProfile(this);
    }

}
