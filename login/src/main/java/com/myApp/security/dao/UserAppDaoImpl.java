package com.myApp.security.dao;

import com.myApp.model.UserApp;
import com.myApp.security.model.Role;
import com.myApp.security.model.UserAppResponse;
import com.myApp.security.model.User_Role;
import com.myApp.security.repository.RoleRepository;
import com.myApp.security.repository.UserRepository;
import com.myApp.security.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAppDaoImpl implements UserAppDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_RoleRepository user_roleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserApp findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserApp saveUser(UserApp user) { return userRepository.save(user); }

    @Override
    public List<UserApp> getUsers() { return userRepository.findAll(); }

    @Override
    public void assignRoleToUser(User_Role user_role) { user_roleRepository.save(user_role); }

    @Override
    public User_Role findRoleByUserId(long user) { return user_roleRepository.findByUser(user); }

    @Override
    public Role getRoleById(long role) { return roleRepository.findById(role); }
}
