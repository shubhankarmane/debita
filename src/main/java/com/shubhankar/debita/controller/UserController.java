package com.shubhankar.debita.controller;

import com.shubhankar.debita.model.User;
import com.shubhankar.debita.request.UserLoginRequest;
import com.shubhankar.debita.request.UserRequest;
import com.shubhankar.debita.response.UserResponse;
import com.shubhankar.debita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest userRequest) {
        User createdUser = userService.createUser(userRequest);
        return new ResponseEntity<>(new UserResponse(createdUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@Valid @RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(userService.verifyUser(request.getEmail(), request.getPassword()), HttpStatus.OK);
    }
}
