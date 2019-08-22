package com.myApp.security.service;

import com.myApp.security.builder.UserAppBuilder;
import com.myApp.security.builder.User_RoleBuilder;
import com.myApp.security.dto.UserAppDto;
import com.myApp.security.exceptions.LoginExceptions;
import com.myApp.security.model.Role;
import com.myApp.security.model.UserApp;
import com.myApp.security.model.User_Role;
import com.myApp.security.repository.RoleRepository;
import com.myApp.security.repository.UserRepository;
import com.myApp.security.repository.User_RoleRepository;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private User_RoleRepository user_roleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserApp us = userRepository.findByUsername(username);
        if (us != null) {
            User_Role u_r = user_roleRepository.findByUser(us.getId());
            Role role = roleRepository.findById(u_r.getRole());
            //TODO cuidado! un usuario puede tener dos roles -> implementar la funcionalidad para ello

            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(role.getRole()));

            UserDetails userDet = new User(us.getUsername(), us.getPassword(), roles);
            return userDet;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserApp save(UserAppDto user) {
        System.out.println(userRepository.findByUsername(user.getUsername()));
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new LoginExceptions.userExistException();
        } else {
            String passw = bcryptEncoder.encode(user.getPassword());
            user.setPassword(passw);
            UserApp newUser = new UserAppBuilder(user).getUserApp();
            UserApp saveUser = userRepository.save(newUser);
            User_Role roleUser =  new User_RoleBuilder(saveUser).getUser_role();
            User_Role saveUserRole = user_roleRepository.save(roleUser);
            return saveUser;
        }

    }

    public List<UserApp> listUsers() {
        List<UserApp> result = userRepository.findAll();
        if (result != null) {
            return result;
        } else {
        throw new UsernameNotFoundException("Users not found");
        }
    }
}