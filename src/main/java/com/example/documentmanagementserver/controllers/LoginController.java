package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.LoginCredentials;
import com.example.documentmanagementserver.models.Role;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    private final UserRepository userRepository;

    @RequestMapping("/login")
    public ResponseEntity<Role> login(@RequestBody LoginCredentials loginCredentials) {
        HashMap<String, String> map = new HashMap<String, String>();
        //System.out.println(getPasswordWithoutSalt(loginCredentials.getPassword()));
        //System.out.println(getPasswordWithoutSalt(userRepository.findByEmailAddress(loginCredentials.getEmailAddress()).getPassword()));

        Optional<User> user = userRepository.findByEmailAddressAndPassword(loginCredentials.getEmailAddress(),
                loginCredentials.getPassword());
        if (user.isPresent()) {
//            map.put("role", user.get().getRole().getCode());
            return new ResponseEntity<>(user.get().getRole(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    private String getPasswordWithoutSalt(String password) {
        int step = (Character.codePointAt(password, 0) % 7) + 2;
        StringBuilder passwordWithoutSalt = new StringBuilder();
        passwordWithoutSalt.append(password.charAt(0));
        for (int i = 1; i < password.length(); i++) {
            if(i % step != 0) {
                passwordWithoutSalt.append(password.charAt(i));
            }
        }
        passwordWithoutSalt.deleteCharAt(0);

        return passwordWithoutSalt.toString();
    }
}
