package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.LoginCredentials;
import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://document-management-client.herokuapp.com"})
public class LoginController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public ResponseEntity<Role> login(@RequestBody LoginCredentials loginCredentials) {
        User user = userRepository.findByEmailAddress(loginCredentials.getEmailAddress());
        if(user == null || !passwordEncoder.matches(loginCredentials.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(user.getRole(), HttpStatus.OK);
    }
}
