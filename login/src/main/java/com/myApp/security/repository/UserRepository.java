package com.myApp.security.repository;

import com.myApp.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Long> {

    UserApp findByUsername(String name);
}
