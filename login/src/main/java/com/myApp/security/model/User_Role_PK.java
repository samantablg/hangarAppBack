package com.myApp.security.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class User_Role_PK implements Serializable {

    private static final long serialVersionUID = -3064410667944568906L;

    private long user;
    private long role;

    public User_Role_PK() { }

    public User_Role_PK(long user_pk, long role_pk) {
        this.user = user_pk;
        this.role = role_pk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_Role_PK that = (User_Role_PK) o;
        return user == that.user &&
                role == that.role;
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, role);
    }
}
