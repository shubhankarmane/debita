package com.shubhankar.debita.controller;

import com.shubhankar.debita.model.User;
import com.shubhankar.debita.request.UserRequest;
import com.shubhankar.debita.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        System.out.println("userRequest = " + userRequest);

        User createdUser = userService.createUser(userRequest);

        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }
}
