package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.dtos.UserNamesForDocumentSenderField;
import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import com.example.documentmanagementserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostMapping("/user/add")
    public ResponseEntity<Integer> addUser(@RequestBody User user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping("/user/table")
    @ResponseBody
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User user : users
        ) {
            user.setPassword("");
        }
        return users;
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable(value = "id") int id) {
        User user = userRepository.findById(id).get();

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        if(Objects.equals(user.getEmailAddress(), username)) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(Objects.equals(user.getRole().getCode(), "ROLE_ADMIN")) {
            if(userRepository.countAllByRole(user.getRole()) <= 1) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<Integer> updateUser(@RequestBody User user) {
        User userSavedInDatabase = userRepository.findById(user.getId()).get();

        if(Objects.equals(user.getRole().getCode(), "ROLE_USER") && Objects.equals(userSavedInDatabase.getRole().getCode(), "ROLE_ADMIN")) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            if(Objects.equals(user.getEmailAddress(), username)) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            if(userRepository.countAllByRole(userSavedInDatabase.getRole()) <= 1) {
                return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (Objects.equals(user.getPassword(), "")) {
            user.setPassword(userSavedInDatabase.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Optional<User> getUser(@PathVariable(value = "id") int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) user.get().setPassword("");
        return user;
    }

    @GetMapping(value="/user/possible-document-senders")
    @ResponseBody
    public List<UserNamesForDocumentSenderField> getUsersNames() {
        return userService.findAndPrepareUsersNames();
    }
}
