package com.myApp.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USER_ROLE")
@IdClass(User_Role_PK.class)
@Getter @Setter
public class User_Role implements Serializable {

    private static final long serialVersionUID = -3064410667944568907L;

    @Id
    @Column(name="user_id")
    private long user;

    @Id
    @Column(name="role_id")
    private long role = 2;

}
