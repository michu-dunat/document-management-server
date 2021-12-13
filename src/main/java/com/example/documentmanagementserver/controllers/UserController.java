package com.example.documentmanagementserver.controllers;

import com.example.documentmanagementserver.models.User;
import com.example.documentmanagementserver.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/user/add")
    public ResponseEntity<Integer> addCase(@RequestBody User user) {
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
    public ResponseEntity<Integer> deleteCase(@PathVariable(value = "id") int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(500, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(200, HttpStatus.OK);
    }

    @PutMapping("/user/update")
    public ResponseEntity<Integer> updateCase(@RequestBody User user) {
        if (user.getPassword() == "") {
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
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
}
