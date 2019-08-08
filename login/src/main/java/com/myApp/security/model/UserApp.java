package com.myApp.security.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
public class UserApp implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private long id;
    @Column(name="name")
    private String username;
    @Column(name="password")
    private String password;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void UserAppRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
}
