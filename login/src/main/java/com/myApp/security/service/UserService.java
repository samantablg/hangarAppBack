/*package com.myApp.security.service;

import com.myApp.security.model.Role;
import com.myApp.security.model.UserApp;
import com.myApp.security.model.User_Role;
import com.myApp.security.repository.RoleRepository;
import com.myApp.security.repository.UserRepository;
import com.myApp.security.repository.User_RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_RoleRepository user_roleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserApp us = userRepository.findByName(username);
        User_Role u_r = user_roleRepository.findByUser(us.getId());
        Role role = roleRepository.findById(u_r.getRole());
        //TODO cuidado! un usuario puede tener dos roles -> implementar la funcionalidad para ello

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority( role.getRole()));

        UserDetails userDet = new User(us.getName(), us.getPassword(), roles);
        return userDet;
    }
}*/