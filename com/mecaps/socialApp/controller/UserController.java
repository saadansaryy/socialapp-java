package com.mecaps.socialApp.controller;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.request.UserRequest;
import com.mecaps.socialApp.response.UserResponse;
import com.mecaps.socialApp.serviceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private Environment env;

    final private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        System.out.println(Arrays.toString(env.getActiveProfiles()));
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
