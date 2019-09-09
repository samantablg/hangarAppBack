package com.myApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER")
@Getter @Setter
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
}
