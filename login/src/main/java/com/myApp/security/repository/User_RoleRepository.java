package com.myApp.security.repository;

import com.myApp.security.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_RoleRepository extends JpaRepository<User_Role, Long> {

    User_Role findByUser(long user_id);
}
