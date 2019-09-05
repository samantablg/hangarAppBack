package com.myApp.security.model;

import com.myApp.userProfile.model.UserProfile;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="user")
public class UserApp implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    /*@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @OneToOne(mappedBy = "id", cascade = CascadeType.ALL)*/
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private long id;
    @Column(name="name")
    private String username;
    @Column(name="password")
    private String password;
    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "userprofile_id")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    /*public void addUserProfile(UserProfile userProfile) {
        userProfile.setId(this);
        this.userProfile = userProfile;
    }

    public void removeUserProfile() {
        if (userProfile != null) {
            userProfile.setId(null);
            this.userProfile = null;
        }
    }*/
}
