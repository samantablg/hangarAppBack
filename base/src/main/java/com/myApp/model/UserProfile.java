package com.myApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "profile")
@Getter @Setter
public class UserProfile {

    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "userApp"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name="user_id", unique = true, nullable = true)
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
