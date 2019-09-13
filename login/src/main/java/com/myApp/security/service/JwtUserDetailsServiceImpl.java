package com.myApp.security.service;

import com.myApp.model.UserProfile;
import com.myApp.security.config.JwtTokenUtil;
import com.myApp.security.dao.UserAppDao;
import com.myApp.security.exceptions.LoginExceptions;
import com.myApp.security.model.Role;
import com.myApp.model.UserApp;
import com.myApp.security.model.User_Role;
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
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserAppDao userAppDao;

    @Autowired
    private JwtTokenUtil tokenUtil;

    @Override //TODO cuidado! un usuario puede tener dos roles -> implementar la funcionalidad para ello

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userAppDao.existsByUsername(username)) {
            UserApp user = userAppDao.findByUsername(username);
            List<GrantedAuthority> roles = getRoleOfUser(user);
            return new User(user.getUsername(), user.getPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserApp save(UserApp user) {
        if (userAppDao.existsByUsername(user.getUsername())) {
            throw new LoginExceptions.userExistException();
        }
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        assignProfile(user);
        UserApp _user = userAppDao.saveUser(user);
        saveUserRole(user);
        return _user;
    }

    public String generateToken(UserDetails userDetails) {
        return tokenUtil.generateToken(userDetails);
    }

    private List<GrantedAuthority> getRoleOfUser(UserApp user) {
        User_Role user_role = userAppDao.findRoleByUserId(user.getId());
        Role role =  userAppDao.getRoleById(user_role.getRole());
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(role.getRole()));
        return roles;
    }

    private void assignProfile(UserApp user) {
        UserProfile profile = new UserProfile(user);
        user.setProfile(profile);
    }

    private User_Role assignRoleToUser(UserApp user) {
        User_Role user_role =  new User_Role();
        user_role.setUser(user.getId());
        return user_role;
    }

    private void saveUserRole(UserApp user) {
        User_Role user_role = assignRoleToUser(user);
        userAppDao.assignRoleToUser(user_role);
    }


}