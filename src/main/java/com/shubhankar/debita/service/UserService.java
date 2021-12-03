package com.shubhankar.debita.service;

import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.UserRepository;
import com.shubhankar.debita.request.UserRequest;
import com.shubhankar.debita.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest userRequest) {
        String hashedPassword = Encrypt.hashPassword(userRequest.getPassword());
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), hashedPassword);
        user = userRepository.save(user);
        return user;
    }

    public User getUser(Integer userId) {
        return userRepository.findById(userId).get();
    }
}
