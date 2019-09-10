package com.myApp.security.service;

import com.myApp.model.UserProfile;
import com.myApp.profile.service.ProfileService;
import com.myApp.security.builder.UserAppBuilder;
import com.myApp.security.builder.User_RoleBuilder;
import com.myApp.security.dto.UserAppDto;
import com.myApp.security.exceptions.LoginExceptions;
import com.myApp.security.model.Role;
import com.myApp.model.UserApp;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Autowired
    private ProfileService profileService;

    @Override
    @TransactionalEventListener
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserApp user = userRepository.findByUsername(username);
        if (user != null) {
            User_Role user_role = user_roleRepository.findByUser(user.getId());
            Role role = roleRepository.findById(user_role.getRole());
            //TODO cuidado! un usuario puede tener dos roles -> implementar la funcionalidad para ello

            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(role.getRole()));

            return new User(user.getUsername(), user.getPassword(), roles);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserApp save(UserAppDto user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new LoginExceptions.userExistException();
        } else {
            user.setPassword(bcryptEncoder.encode(user.getPassword()));
            UserApp new_user = new UserAppBuilder(user).getUserApp();
            UserApp save_user = userRepository.save(new_user);
            User_Role role_user =  new User_RoleBuilder(save_user).getUser_role();
            user_roleRepository.save(role_user);
            UserProfile profile = new UserProfile(save_user);
            return save_user;
        }
    }

    public List<UserApp> listUsers() {
        List<UserApp> result = userRepository.findAll();
        if (!result.isEmpty())
            return result;
        throw new UsernameNotFoundException("Users not found");
    }

    public boolean existsByUsername(String username) {
        UserApp user = userRepository.findByUsername(username);
        return (user != null);
    }

}