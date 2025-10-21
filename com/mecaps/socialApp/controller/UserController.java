package com.mecaps.socialApp.controller;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.request.LoginDTO;
import com.mecaps.socialApp.request.UserRequest;
import com.mecaps.socialApp.response.UserResponse;
import com.mecaps.socialApp.security.JwtService;
import com.mecaps.socialApp.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    private Environment env;

    final private UserServiceImpl userService;
    private final JwtService jwtService;

    public UserController(UserServiceImpl userService, JwtService jwtService){

        this.userService = userService;
        this.jwtService = jwtService;

    }

//    @PostMapping("/create")
//    public Map<String, String> login(@RequestBody LoginDTO request){
//        String token = jwtService.generateAccessToken(request.getEmail(),request.getRole());
//
//        Map<String, String> map = new HashMap<>();
//        map.put("Access Token",token);
//        map.put("Role",request.getRole());
//        return map;
//    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @GetMapping("/findByUserName")
    public List<User> getUserByNameUsingCriteria(@RequestParam String userName){
        return userService.getUserByNameUsingCriteriaAPI(userName);
    }

    @GetMapping("/by-email")
    public User findByUserEmail(@RequestParam String email){
        return userService.findByUserEmail(email);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        return userService.updateUser(userRequest,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }
}
