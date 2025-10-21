package com.mecaps.socialApp.serviceImplementation;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

//        String role = user.getRole().startsWith("ROLE_") ? user.getRole() : "ROLE_" + user.getRole();
//
//        GrantedAuthority userRole = new SimpleGrantedAuthority(role);
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserName(),
//                user.getPassword(),
//                Collections.singleton(userRole));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
