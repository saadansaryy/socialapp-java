package com.mecaps.socialApp.controller;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.repository.UserRepository;
import com.mecaps.socialApp.request.AuthDTO;
import com.mecaps.socialApp.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AuthController(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthDTO request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User NOT FOUND : " + request.getEmail()));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtService.generateAccessToken(user.getEmail(),user.getRole());

        Map<String, String> map = new HashMap<>();

        map.put("Token : ",token);
        map.put("Role : ",user.getRole());
        map.put("Username : ",user.getUserName());
        return map;
    }
}
