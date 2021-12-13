package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.LoginCredentials;
import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public ResponseEntity<Role> login(@RequestBody LoginCredentials loginCredentials) {
        User user1 = userRepository.findByEmailAddress(loginCredentials.getEmailAddress());
        if(passwordEncoder.matches(loginCredentials.getPassword(), user1.getPassword())) {
            return new ResponseEntity<>(user1.getRole(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }
}
