package com.mecaps.socialApp.serviceImplementation;

import com.mecaps.socialApp.entity.User;
import com.mecaps.socialApp.exception.UserNotFoundException;
import com.mecaps.socialApp.repository.UserRepository;
import com.mecaps.socialApp.request.UserRequest;
import com.mecaps.socialApp.response.UserResponse;
import com.mecaps.socialApp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserResponse> userResponse = userList.stream().map(UserResponse::new).toList();
        return ResponseEntity.ok(userResponse);
    }

    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        return new UserResponse(user);
    }

    public User findByUserEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email : " + email));
    }

    public ResponseEntity<?> createUser(UserRequest userRequest) {
        User user = new User();

        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        User save = userRepository.save(user);

        UserResponse userResponse = new UserResponse(save);

//        return ResponseEntity.ok("User created");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message","User created successfully",
                        "body",userResponse,
                        "success",true
                ));
    }

    public UserResponse updateUser(UserRequest userRequest, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));

        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        User save = userRepository.save(user);
        return new UserResponse(save);
    }

    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
        userRepository.delete(user);
        return "User with id : " + id + " deleted successfully";
    }
}
