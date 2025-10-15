package com.mecaps.socialApp.service;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.request.UserRequest;
import com.mecaps.socialApp.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<?> getAllUsers();

    UserResponse getUserById(Long id);

    User findByUserEmail(String email);

    ResponseEntity<?> createUser(UserRequest userRequest);

    UserResponse updateUser(UserRequest userRequest, Long id);

    String deleteUser(Long id);

    List<User> getUserByNameUsingCriteriaAPI(String userName);
}
