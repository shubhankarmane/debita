package com.shubhankar.debita.service;

import com.shubhankar.debita.exception.AuthException;
import com.shubhankar.debita.model.User;
import com.shubhankar.debita.repository.UserRepository;
import com.shubhankar.debita.request.UserRequest;
import com.shubhankar.debita.util.Encrypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private Map<String, String> generateJWT(User user) {
        Map<String, String> result = new HashMap<>();

        long currentTimestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, "example_demo")
                .setIssuedAt(new Date(currentTimestamp))
                .setExpiration(new Date(currentTimestamp + (2 * 60 * 60 * 1000)))
                .claim("userId", user.getId())
                .compact();

        result.put("token", token);
        return result;
    }

    public Map<String, String> verifyUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        if(user == null)
            throw new AuthException("Invalid email");

        if(Encrypt.checkPassword(user.getPassword(), password)) {
            return generateJWT(user);
        }

        throw new AuthException("Invalid password");
    }
}
