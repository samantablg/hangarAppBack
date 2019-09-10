package com.myApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user")
@Getter @Setter
public class UserApp implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id", unique = true, nullable = false)
    private long id;
    @Column(name="name")
    private String username;
    @Column(name="password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userApp", fetch = FetchType.LAZY)
    private UserProfile profile;

}
